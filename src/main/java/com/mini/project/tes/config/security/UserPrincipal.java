package com.permata.recurring.core.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.permata.recurring.core.model.dto.Role;
import com.permata.recurring.core.model.dto.User;

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
    private User user;
	private Long id;

    private String username;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;

    //private String loginIp;
    //private String workgroup;
    
    //private Collection<? extends GrantedAuthority> authorities;
    private Collection<SimpleGrantedAuthority> authorities;

//    public UserPrincipal(Long id, String username, String email, String password, Collection<SimpleGrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }

    public UserPrincipal(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserPrincipal(User user){
        this.user = user;
        this.username = user.getUsername();
        this.id = user.getId();
        authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
    }

    public static UserPrincipal create(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList()));
        /*
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );

         */
        return new UserPrincipal(user);
    }
    
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public User getUser() {
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
