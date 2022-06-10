package com.mini.project.tes.config.security;

import com.mini.project.tes.config.security.entity.SamUserEntity;
import com.mini.project.tes.persistence.repository.SamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SamUserRepository samUserService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        SamUserEntity user = samUserService.findByUsername(usernameOrEmail);
        if(user == null) {
            new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }
        return UserPrincipal.create(user);
    }
    @Transactional
    public UserDetails loadUserById(long id) {
        try {
            SamUserEntity user = samUserService.getById(id);
//            AtfUmaUserAssignment atfUmaUserAssignment = atfUmaUserAssignmentService.findByUserAndStatusActive(samUserAssembler.convertToEntity(user));

            return UserPrincipal.create(user);
		} catch (Exception e) {
			new ResourceNotFoundException("User id="+id);
			return null;
		}
    }
}