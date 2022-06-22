package com.pudge.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.user.mapper.AddressMapper;
import com.pudge.user.service.AddressService;
import com.wz.api.user.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService  {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(String username) {
        QueryWrapper<Address> queryWrapper =new QueryWrapper<Address>();
        queryWrapper.eq("username",username);

        return addressMapper.selectList(queryWrapper);
    }
}
