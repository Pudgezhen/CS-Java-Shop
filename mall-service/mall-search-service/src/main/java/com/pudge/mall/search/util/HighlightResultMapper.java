package com.pudge.mall.search.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.ByteBufferReference;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HighlightResultMapper extends DefaultResultMapper {


    /**
     * 映射转换，将非高亮数据转换成高亮数据
     *
     * @param response
     * @param clazz
     * @param pageable
     * @param <T>
     * @return
     */
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        //1. 获取非高亮数据
        SearchHits hits = response.getHits();
        //2. 循环非高亮数据
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //3. 获取高亮数据
            for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
                //4.将非高亮数据转换成高亮数据
                String key = entry.getKey();
                //如果当前非高亮对象中有该高亮数据对应的非高亮对象，则进行替换
                if (sourceAsMap.containsKey(key)) {
                    //高亮碎片
                    Text[] fragments = entry.getValue().getFragments();
                    String str = transTxtToArrayToString(fragments);

                    if (!StringUtils.isEmpty(str)) {
                        //替换高亮
                        sourceAsMap.put(key, str);
                    }
                    }
                }
            //更新hit的数据
            hit.sourceRef(new ByteBufferReference(ByteBuffer.wrap(JSONObject.toJSONString(sourceAsMap).getBytes(StandardCharsets.UTF_8))));
            }
        return super.mapResults(response, clazz, pageable);
    }


    public String transTxtToArrayToString(Text[] fragments) {
        if (fragments != null) {
            StringBuilder buffer = new StringBuilder();
            for (Text fragment : fragments) {
                buffer.append(fragment.toString());
            }
            return buffer.toString();
        }
        return null;
    }
}

