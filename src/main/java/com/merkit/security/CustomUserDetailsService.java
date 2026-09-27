package com.merkit.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.merkit.entity.User;
import com.merkit.repo.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {


        User user =  username.contains("@") ?
                userRepository
                .findByEmailWithRoles(username)
                .orElseThrow(
                    () -> new UsernameNotFoundException(
                            "User not found"
                    )
                )
        :
        	userRepository
            .findByUsernameWithRoles(username)
            .orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found"
                )
            );


        return new CustomUserDetails(user);
    }
    
}