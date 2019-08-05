package com.test.mapper;

import com.test.entity.ProjectExternal;

/**
 * 项目信息
 * Created by zhangxuefei on 2018/8/7.
 */
public interface ProjectExternalMapper {

    /**
     * 根据id获得对象
     * @param id
     * @return
     */
    ProjectExternal queryById(Long id);

}
