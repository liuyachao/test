package com.test.mapper;

import com.test.entity.User3;

public interface User3Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User3 record);

    int insertSelective(User3 record);

    User3 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User3 record);

    int updateByPrimaryKey(User3 record);
}