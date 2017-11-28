package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.spring.springboot.dao.ds1.mapper.Ds1Mapper;
import org.spring.springboot.dao.ds2.mapper.Ds2Mapper;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Ds1Mapper ds1Mapper; // ds1数据源

    @Autowired
    private Ds2Mapper ds2Mapper; // ds2数据源

    @Autowired
    private Ds2Mapper ds3Mapper; // ds2数据源

    @Override
    public JSONObject findByName(String userName) {
        JSONObject obj = new JSONObject();
        obj.put("lilei", JSON.toJSONString(ds1Mapper.getUserInfo()));
        obj.put("hameimei", JSON.toJSONString(ds2Mapper.getUserInfo()));
        obj.put("123", JSON.toJSONString(ds3Mapper.getUserInfo()));

        return obj;
    }
}
