/*
package com.mixin.demo.ssm.mybatis;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {
   @Select("select * from table1")
    @Results({
      @Result(property = "id", column = "id", id = true),
      @Result(property = "value", column = "value")
    })
    List<table1> getAll();

    @Select("select * from table1 where id = #{uid}")
    public table1 find(int uid);

}
        */