package com.example.exchangeapp.mapper;

import com.example.exchangeapp.domain.UserEntity;
import com.example.exchangeapp.dto.UserDto;
import com.example.exchangeapp.service.UserService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface UserMapper {

    UserDto mapToUserDto(UserEntity userEntity);
}
