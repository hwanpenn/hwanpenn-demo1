package com.example.hwanpenndemo1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper2 {

    @Update("update account set money = #{money} where id = #{id}")
    int transMoney(@Param("money") double money, @Param("id") int  id);
}
