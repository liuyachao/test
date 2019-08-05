package com.test.service;

import com.test.entity.User2;
import com.test.entity.User3;

/**
 * Created by liuyachao on 2018/9/3.
 */
public interface TestBService {
    int saveUser2 (User2 user2);

    User3 getUser3List(User3 user3);
}
