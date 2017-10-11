package com.huawei.csic.service;

import java.io.InputStream;
import java.util.List;

import com.huawei.csic.entity.UserEntity;

public interface IUserService {
    String findAge(String id);
    
    List<UserEntity> getUser(int pageNum, int pageSize);

    public InputStream getInputStream(int pageNum, int pageSize) throws Exception;
}
