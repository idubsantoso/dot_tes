package com.mini.project.tes.repository;

import com.mini.project.tes.model.entity.SamUserEntity;
import com.mini.project.tes.util.BaseHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SamUserRepositoryTest {
    @Autowired
    private SamUserRepository underTest;
//    @Test
    void itShouldCheckByUsername() throws Exception {
        BaseHelper baseHelper=new BaseHelper();
        //given
        SamUserEntity user=new SamUserEntity();
        user.setUsername("admin");
        user.setPassword(baseHelper.getPasswordMD5("password"));
        underTest.save(user);

        //when
        SamUserEntity expected=underTest.findByUsername(user.getUsername());

        //then
        assertThat(expected);
    }
}