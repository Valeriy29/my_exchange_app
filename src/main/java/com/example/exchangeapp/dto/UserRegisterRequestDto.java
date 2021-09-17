package com.example.exchangeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;
}