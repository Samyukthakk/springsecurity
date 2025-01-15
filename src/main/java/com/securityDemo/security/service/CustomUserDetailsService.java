package com.securityDemo.security.service;

import com.securityDemo.security.CustomUserDetails;
import com.securityDemo.security.entity.User;
import com.securityDemo.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(!Objects.isNull(user)) {
            System.out.println("user not avail");
            throw new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}
