package com.example.exchangeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

    @NotNull
    private String username;

    @NotNull
    private String token;
}