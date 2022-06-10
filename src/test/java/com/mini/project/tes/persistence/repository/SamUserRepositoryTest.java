package com.mini.project.tes.persistence.repository;

import com.mini.project.tes.config.security.entity.SamUserEntity;
import com.mini.project.tes.utility.BaseHelper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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