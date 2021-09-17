package com.example.exchangeapp.rest;

import com.example.exchangeapp.dto.AuthenticationRequestDto;
import com.example.exchangeapp.dto.AuthenticationResponseDto;
import com.example.exchangeapp.service.AuthenticationService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value="Authorization endpoints")
@RestController
@RequestMapping(value = "api/v1/auth/")
@AllArgsConstructor
public class AuthenticationRestControllerV1 {

    private final AuthenticationService authenticationService;

    @ApiOperation(value = "User login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authorization successful"),
            @ApiResponse(code = 403, message = "Access denied, authorization failed"),
    })
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> login(@ApiParam(value = "User login", required = true) @RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }
}
