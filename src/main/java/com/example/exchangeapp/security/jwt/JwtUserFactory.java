package com.example.exchangeapp.security.jwt;

import com.example.exchangeapp.domain.Role;
import com.example.exchangeapp.domain.Status;
import com.example.exchangeapp.domain.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(UserEntity userEntity) {
        return new JwtUser(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getStatus().equals(Status.ACTIVE),
                userEntity.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(userEntity.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                    new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }

}
