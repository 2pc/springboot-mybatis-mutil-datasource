package org.spring.springboot.dao.ds1.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.spring.springboot.domain.User;

/**
 * Created by luping on 2017/11/28.
 */
public interface Ds1Mapper {

    @Select("select * from tb1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")})
    public User getUserInfo();

}
