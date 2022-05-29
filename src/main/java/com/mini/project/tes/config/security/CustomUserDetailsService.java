package com.mini.project.tes.config.security;

import com.permata.recurring.core.assembler.SamUserAssembler;
import com.permata.recurring.core.model.dto.User;
import com.permata.recurring.core.service.AtfUmaUserAssignmentService;
import com.permata.recurring.core.service.SamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Winner [Alpabit]
 *
 * Dec 4, 2019
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SamUserService samUserService;
    @Autowired
    private AtfUmaUserAssignmentService atfUmaUserAssignmentService;
    @Autowired
    private SamUserAssembler samUserAssembler;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        User user = samUserService.findByUsername(usernameOrEmail);
        if(user == null) {
        	new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(long id) {
        try {
            User user = samUserService.findById(id);
//            AtfUmaUserAssignment atfUmaUserAssignment = atfUmaUserAssignmentService.findByUserAndStatusActive(samUserAssembler.convertToEntity(user));

            return UserPrincipal.create(user);
		} catch (Exception e) {
			new ResourceNotFoundException("User id="+id);
			return null;
		}
    }
}