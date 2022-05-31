package com.mini.project.tes.service.impl;

import com.mini.project.tes.model.entity.SamUserEntity;
import com.mini.project.tes.repository.SamUserRepository;
import com.mini.project.tes.service.UserService;
import com.mini.project.tes.service.error.ServiceException;
import com.mini.project.tes.util.BaseHelper;
import com.mini.project.tes.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SamUserRepository repo;
    @Autowired
    private LogUtil logUtil;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<SamUserEntity> findAll() {
        try {
            List<SamUserEntity> entities = repo.findAll();
            return entities;
        } catch (Exception e) {
            log.error("Error find all in " + this.getClass().getName() + ":" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public SamUserEntity findByUsername(String username) {
        try {
            SamUserEntity entities = repo.findByUsername(username);
            return entities;
        } catch (Exception e) {
            log.error("Error findByUsername in " + this.getClass().getName() + ":" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public SamUserEntity save(SamUserEntity userEntity) {
        try {
            SamUserEntity entity = repo.saveAndFlush(userEntity);
            logUtil.success(this.getClass().getName() +" [Save]", "id: " + userEntity.getId());

            return entity;
        } catch (Exception e) {
            log.error("Error save in "  + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }

    @Override
    public SamUserEntity update(SamUserEntity userEntity) {
        try {
            BaseHelper baseHelper =  new BaseHelper();
            SamUserEntity entity = repo.findById(userEntity.getId()).get();
            entity.setUsername(userEntity.getUsername());
            entity.setPassword(baseHelper.getPasswordMD5(userEntity.getPassword()));


            SamUserEntity entityResult = repo.saveAndFlush(entity);
            logUtil.success(this.getClass().getName() +" [Update]", "id: " + entity.getId());
            return entityResult;
        } catch (Exception e) {
            log.error("Error update in " + this.getClass().getName() + " :" + e.getMessage());
            throw new  ServiceException(e.getMessage());
        }
    }
}
