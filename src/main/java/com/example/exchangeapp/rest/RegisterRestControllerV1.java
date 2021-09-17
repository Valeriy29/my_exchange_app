package com.example.exchangeapp.rest;

import com.example.exchangeapp.dto.UserRegisterRequestDto;
import com.example.exchangeapp.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Registration endpoints")
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/register")
public class RegisterRestControllerV1 {

    private final UserService userService;

    @ApiOperation(value = "User registration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registration completed successfully"),
            @ApiResponse(code = 204, message = "Registration failed"),
    })
    @PostMapping
    public ResponseEntity<UserRegisterRequestDto> saveUser(@ApiParam(value = "Registration request", required = true)
                                                           @RequestBody @Valid UserRegisterRequestDto request) {
        UserRegisterRequestDto userRegisterRequestDto = userService.register(request);
        if (userRegisterRequestDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userRegisterRequestDto, HttpStatus.OK);
    }
}
