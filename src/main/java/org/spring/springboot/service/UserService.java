package org.spring.springboot.service;

import com.alibaba.fastjson.JSONObject;


public interface UserService {

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    JSONObject findByName(String userName);
}
