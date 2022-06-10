package com.mini.project.tes.persistence.service;

import com.mini.project.tes.security.entity.SamUserEntity;

import java.util.List;

public interface UserService {
    List<SamUserEntity> findAll();
    SamUserEntity save(SamUserEntity userEntity);
    SamUserEntity update(SamUserEntity userEntity);
    SamUserEntity findByUsername(String username);
}
