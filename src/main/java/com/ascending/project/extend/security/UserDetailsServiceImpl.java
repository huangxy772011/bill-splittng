package com.ascending.project.extend.security;

import com.ascending.project.domain.Authority;
import com.ascending.project.domain.User;
import com.ascending.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String emailorUsername) throws UsernameNotFoundException {

        User domainUser = null;
        try{
//            domainUser = userService.findByEmailOrUsername(emailorUsername);
        }catch (Exception repositoryProblem){
            logger.debug("catch AuthenticationServiceException from AuthenticationProvider");
            throw new UsernameNotFoundException("cannot find username in the database: " + domainUser.getUsername());
        }
        if (domainUser == null){
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.UsernameNotFound " + emailorUsername);
        }
//        TODO
//        List<Authority> userAuthorities = userService.findAuthorities(domainUser);
//        domainUser.setAuthorities(userAuthorities);
        return domainUser;
    }

}
