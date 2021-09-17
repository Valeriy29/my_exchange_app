package com.example.exchangeapp.security;

import com.example.exchangeapp.domain.UserEntity;
import com.example.exchangeapp.security.jwt.JwtUser;
import com.example.exchangeapp.security.jwt.JwtUserFactory;
import com.example.exchangeapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByUsername(username);

        if(userEntity == null) {
            throw new UsernameNotFoundException("User with username:" + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(userEntity);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
