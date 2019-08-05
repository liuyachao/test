package com.test.provider;

import com.alibaba.fastjson.JSONObject;
import com.test.core.TestSupport;
import com.test.entity.User2;
import com.test.entity.User3;
import com.test.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/** 
  * TestServiceTest
  * @author: liuyachao 
  * 2019/4/16 17:15
 */
public class TestServiceTest extends TestSupport {
    @Autowired
    private TestService testService;

    /**
     * 测试事务
     * @throws ParseException
     */
    @Test
    public void insertSelectiveTest() throws ParseException {
        User2 user2 = new User2();
        user2.setId(1006);
        user2.setUsername("测试事务2");
        user2.setPassword("123456");
        int result = testService.saveUser2(user2);
        System.err.println("******************************************");
        System.err.println(JSONObject.toJSONString(result));
    }

    /**
     * 测试返回字段类型为boolean类型字段
     * @throws ParseException
     */
    @Test
    public void getUser3ListTest() throws ParseException {
        User3 user3 = new User3();
        user3.setId(1);
        User3 result = testService.getUser3List(user3);
        System.err.println("******************************************");
        System.err.println(JSONObject.toJSONString(result));
    }

    @Test
    public void threadMethodTest() throws ParseException {
        testService.threadMethod();
        System.err.println("******************************************");
    }
}
