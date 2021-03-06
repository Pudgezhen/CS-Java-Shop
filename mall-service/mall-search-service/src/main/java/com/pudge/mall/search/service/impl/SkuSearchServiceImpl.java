package com.pudge.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.pudge.mall.search.mapper.SkuSearchMapper;
import com.pudge.mall.search.service.SkuSearchService;
import com.pudge.mall.search.util.HighlightResultMapper;
import com.wz.api.search.model.SkuES;
import com.wz.mall.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private SkuSearchMapper skuSearchMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public void add(SkuES skuES) {
        String attrMap = skuES.getSkuAttribute();
        if (!StringUtils.isEmpty(attrMap)) {
            skuES.setAttrMap(JSON.parseObject(attrMap, Map.class));
        }
        skuSearchMapper.save(skuES);
    }

    @Override
    public void del(String id) {
        skuSearchMapper.deleteById(id);
    }

    /**
     * ???????????????
     *
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //??????????????????
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);

        //????????????
        group(queryBuilder, searchMap);

     //????????????
        //1.???????????????????????????????????????????????????
        HighlightBuilder.Field field = new HighlightBuilder
                                                    .Field("name")       //????????????????????????????????????
                                                    .preTags("<span style=\"color:red\">")         // ?????????????????????
                                                    .postTags("</span>")       // ?????????????????????
                                                    .fragmentSize(100);        // ????????????
        queryBuilder.withHighlightFields(field);
        //2.???????????????????????????????????????

        //????????????
//        Page<SkuES> page = skuSearchMapper.search(queryBuilder.build());
//        AggregatedPage<SkuES> page = (AggregatedPage<SkuES>) skuSearchMapper.search(queryBuilder.build());
        AggregatedPage<SkuES> page = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), SkuES.class,new HighlightResultMapper());

        //????????????
        Map<String, Object> resultMap = new HashMap<>();
        //??????????????????
        Aggregations aggregations = page.getAggregations();
        if (aggregations != null) {
            parseGroup(aggregations, resultMap);
        }
        //??????????????????
        attrParse(resultMap);
        List<SkuES> skuESList = page.getContent();
        resultMap.put("list", skuESList);

        //??????????????????
        NativeSearchQuery build = queryBuilder.build();
        Pageable pageable = build.getPageable();
        int currentPage = pageable.getPageNumber()+1;
        PageInfo pageInfo = new PageInfo(page.getTotalElements(),currentPage,5);
        resultMap.put("pageInfo",pageInfo);
        resultMap.put("totalElements", page.getTotalElements());
        return resultMap;
    }

    //??????????????????
    public NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // ??????????????????
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (searchMap != null && searchMap.size() > 0) {
            // ???????????????
            Object keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty((String) keywords)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("name", keywords.toString()));
            }

            //????????????
            Object category = searchMap.get("category");
            if (!StringUtils.isEmpty((String) category)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", category.toString()));
            }

            //????????????
            Object brand = searchMap.get("brand");
            if (!StringUtils.isEmpty((String) brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand.toString()));
            }

            //??????????????????   0-500   500-1000   1000+
            Object price = searchMap.get("price");
            if (!StringUtils.isEmpty((String) price)) {
                String[] prices = price.toString().replace("???", "").replace("??????", "").split("-");
                //price > x
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.valueOf(prices[0])));
                //price < y
                if (prices.length == 2) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.valueOf(prices[1])));
                }
            }

            //??????????????????
            for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
                if (entry.getKey().startsWith("attr_")) {
                    boolQueryBuilder.must(QueryBuilders.termQuery("attrMap." + entry.getKey().replaceFirst("attr_", "") + ".keyword", entry.getValue().toString()));
                }
            }

            //??????
            Object sfield = searchMap.get("sfield");
            Object sm = searchMap.get("sm");
            if(!StringUtils.isEmpty((String)sfield) && !StringUtils.isEmpty((String)sm)) {
                queryBuilder.withSort(
                        SortBuilders.fieldSort(sfield.toString())                 // ???????????????
                                .order(SortOrder.valueOf(sm.toString()))    // ??????????????????
                );
            }
        }
        // ????????????
        queryBuilder.withPageable(PageRequest.of(currentPage(searchMap), 5));
        return queryBuilder.withQuery(boolQueryBuilder);
    }

    // ????????????
    public int currentPage(Map<String, Object> searchMap) {
        try {
            Object page = searchMap.get("page");
            return Integer.valueOf(page.toString()) - 1;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * ????????????
     */
    public NativeSearchQueryBuilder group(NativeSearchQueryBuilder queryBuilder, Map<String, Object> searchMap) {
        //???????????????????????????????????????????????????????????????????????????????????????????????????
        if (StringUtils.isEmpty((String) searchMap.get("category"))) {
            queryBuilder.addAggregation(
                    AggregationBuilders
                            .terms("categoryList")
                            .field("categoryName").size(100)
            );
        }
        //???????????????????????????????????????????????????????????????????????????????????????????????????
        if (StringUtils.isEmpty((String) searchMap.get("brand"))) {
            queryBuilder.addAggregation(
                    AggregationBuilders
                            .terms("brandList")
                            .field("brandName").size(100)
            );
        }

        queryBuilder.addAggregation(
                AggregationBuilders
                        .terms("attrmaps")
                        .field("skuAttribute").size(10000)
        );
        return queryBuilder;
    }

    /**
     * ??????????????????
     *
     * @param aggregations
     * @param groupMap
     */
    public void parseGroup(Aggregations aggregations, Map<String, Object> groupMap) {
        for (Aggregation aggregation : aggregations) {
            ParsedStringTerms terms = (ParsedStringTerms) aggregation;


            List<String> values = new ArrayList<>();
            for (Terms.Bucket bucket : terms.getBuckets()) {
                values.add(bucket.getKeyAsString());
            }
            String key = aggregation.getName();
            groupMap.put(key, values);
        }
    }

    /**
     * ????????????????????????Map??????
     *
     * @param searchMap
     */
    public void attrParse(Map<String, Object> searchMap) {
        Object attrmaps = searchMap.get("attrmaps");
        if (attrmaps != null) {
            List<String> groupList = (List<String>) attrmaps;
            Map<String, Set<String>> allMaps = new HashMap<>();

            for (String attr : groupList) {
                Map<String, String> attrMap = JSON.parseObject(attr, Map.class);
                for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                    String key = entry.getKey();
                    Set<String> values = allMaps.get(key);
                    if (null == values) {
                        values = new HashSet<>();
                    }
                    values.add(entry.getValue());
                    allMaps.put(key, values);
                }
            }
            searchMap.put("attrmaps", allMaps);
        }


    }


}
