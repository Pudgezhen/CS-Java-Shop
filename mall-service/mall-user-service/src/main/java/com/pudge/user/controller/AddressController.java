package com.pudge.user.controller;

import com.pudge.user.service.AddressService;
import com.wz.api.user.model.Address;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {


    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public RespResult<List<Address>> list(){
        String username = "wz";
        List<Address> addresses = addressService.list(username);
        return RespResult.ok(addresses);
    }
}
