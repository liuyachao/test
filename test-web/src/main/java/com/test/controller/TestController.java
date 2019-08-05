package com.test.controller;

import com.common.common.CodeConts;
import com.common.util.Tool;
import com.test.entity.User2;
import com.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/** 
  * TestController 
  * @author: liuyachao 
  * 2018/9/4 13:35
 */
@Controller
@Api(value = "测试controller")
@RequestMapping(value = "/api/test")
@Slf4j
public class TestController {

    @Resource
    private TestService testService;

    @ApiOperation("测试")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@ApiParam(name = "customerNo", value = "客户编号") @RequestParam Long customerNo) {
        /*Map<String, Object> map = new HashMap<>();
        try {
            User2 user2 = new User2();
            user2.setUsername("test1");
            user2.setPassword("123456");
            int result = testService.insertSelective(user2);
            map = Tool.resultMap(CodeConts.SYS_ERR, "保存成功!");
            map.put("data",result);
            return map;
        } catch (Exception e) {
            log.error("执行方法：queryList异常：{}", e);
            return Tool.resultMap(CodeConts.SYS_ERR, "保存异常!");
        }*/
        return null;
    }

}
