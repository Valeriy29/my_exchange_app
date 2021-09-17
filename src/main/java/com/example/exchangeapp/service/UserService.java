package com.example.exchangeapp.service;

import com.example.exchangeapp.domain.Role;
import com.example.exchangeapp.domain.Status;
import com.example.exchangeapp.domain.UserEntity;
import com.example.exchangeapp.dto.UserDto;
import com.example.exchangeapp.dto.UserRegisterRequestDto;
import com.example.exchangeapp.exception.NotFoundException;
import com.example.exchangeapp.mapper.RegisterUserMapper;
import com.example.exchangeapp.mapper.UserMapper;
import com.example.exchangeapp.repository.RoleRepository;
import com.example.exchangeapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RegisterUserMapper registerUserMapper;
    private final UserMapper userMapper;

    public UserRegisterRequestDto register(UserRegisterRequestDto userRegisterRequestDto) {

        UserEntity userEntity = registerUserMapper.mapToUserEntity(userRegisterRequestDto);

        if (userRepository.findByUsernameOrEmail(userEntity.getUsername(), userEntity.getEmail()).isPresent()) {
           return null;
        }

        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles= new ArrayList<>();
        userRoles.add(role);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(userRoles);
        userEntity.setStatus(Status.ACTIVE);

        UserEntity registeredUser = userRepository.save(userEntity);
        log.info("IN register - user: {} successfully registered", registeredUser);

        return registerUserMapper.mapToUserDto(registeredUser);
    }

    public List<UserEntity> getAll() {
        List<UserEntity> users = userRepository.findAll();
        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    public UserEntity findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", userEntity, username);
        return userEntity;
    }

    public UserEntity findById(Long id) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        log.info("IN findById - user: {} found by id: {}", userEntity, id);
        return  userEntity;
    }

    public UserDto findUserInfoByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return null;
        }
        return userMapper.mapToUserDto(userEntity);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
