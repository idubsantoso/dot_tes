package com.mini.project.tes.service;

import com.mini.project.tes.model.entity.SamUserEntity;

import java.util.List;

public interface UserService {
    List<SamUserEntity> findAll();
    SamUserEntity save(SamUserEntity userEntity);
    SamUserEntity update(SamUserEntity userEntity);
    SamUserEntity findByUsername(String username);
}
