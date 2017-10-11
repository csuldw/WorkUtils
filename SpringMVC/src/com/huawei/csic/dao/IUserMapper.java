package com.huawei.csic.dao;

import java.util.List;

import com.huawei.csic.entity.UserEntity;

public interface IUserMapper {
    String findAge(String id);
    
    List<UserEntity> getUser();
}
