package com.example.exchangeapp.rest;

import com.example.exchangeapp.dto.UserDto;
import com.example.exchangeapp.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Api(value="User endpoints")
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/users/")
public class UserRestControllerV1 {

    private final UserService userService;

    @ApiOperation(value = "Find user info", authorizations = { @Authorization(value="Bearer Token") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information about yourself successfully received"),
            @ApiResponse(code = 204, message = "Information not found"),
            @ApiResponse(code = 403, message = "Access denied, authorization required")
    })
    @GetMapping("user-info")
    public ResponseEntity<UserDto> findUserInfo(Principal principal) {
        UserDto userDto = userService.findUserInfoByUsername(principal.getName());
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
