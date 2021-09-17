package com.example.exchangeapp.mapper;

import com.example.exchangeapp.domain.UserEntity;
import com.example.exchangeapp.dto.UserRegisterRequestDto;
import com.example.exchangeapp.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface RegisterUserMapper {

    @Mappings({
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    UserEntity mapToUserEntity(UserRegisterRequestDto request);

    UserRegisterRequestDto mapToUserDto(UserEntity userEntity);
}
