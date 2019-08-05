package com.test.impl;


import com.test.entity.User2;
import com.test.entity.User3;
import com.test.mapper.User2Mapper;
import com.test.mapper.User3Mapper;
import com.test.service.TestBService;
import com.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by liuyachao on 2018/9/3.
 */
@Slf4j
@Service
public class TestBServiceImpl implements TestBService {
    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private User3Mapper user3Mapper;

    int count = 112;

    static int countTest = 0;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int saveUser2(User2 user2){
        Integer result = 0;
        /*try{*/
            result = user2Mapper.insertSelective(user2);
            if(user2.getId() == 114){
                int i = 1/0;
            }
        /*}catch (Exception e){
            log.error("插入异常",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }*/
        return result;
    }

    @Override
    public User3 getUser3List(User3 user3) {
        User3 result =user3Mapper.selectByPrimaryKey(user3.getId());
        return result;
    }

}
