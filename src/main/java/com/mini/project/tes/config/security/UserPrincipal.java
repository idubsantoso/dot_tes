package com.mini.project.tes.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.project.tes.model.dto.User;
import com.mini.project.tes.model.entity.SamUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Winner [Alpabit]
 *
 * Dec 4, 2019
 */
public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 3545972395145669882L;
    private static Collection<SimpleGrantedAuthority> authorities;
    private SamUserEntity user;
	private Long id;

    private String username;
    @JsonIgnore
    private String password;

    //private String loginIp;
    //private String workgroup;
    
    //private Collection<? extends GrantedAuthority> authorities;


    public UserPrincipal(Long id, String username, String password, Collection<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserPrincipal create(SamUserEntity user) {

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);

    }

    public UserPrincipal(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserPrincipal(SamUserEntity user){
        this.user = user;
        this.username = user.getUsername();
        this.id = user.getId();
        authorities = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public SamUserEntity getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    /*
    public String getLoginIp() {
        return loginIp;
    }

    public String getWorkgroup() {
        return workgroup;
    }

     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
