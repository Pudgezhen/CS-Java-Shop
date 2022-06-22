package com.pudge.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.user.model.Address;

import java.util.List;

public interface AddressService extends IService<Address> {

    List<Address> list(String username);
}
