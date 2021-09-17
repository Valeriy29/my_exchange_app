package com.example.exchangeapp.rest;

import com.example.exchangeapp.domain.UserEntity;
import com.example.exchangeapp.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="Admins endpoints")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @ApiOperation(value = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information about user successfully received"),
            @ApiResponse(code = 204, message = "Information not found"),
            @ApiResponse(code = 403, message = "Access denied, administrator rights required"),
    })
    @GetMapping(value = "find-user-by-id/{id}")
    public ResponseEntity<UserEntity> findUserById(@ApiParam(value = "User id", required = true) @PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @ApiOperation(value = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information about user successfully received"),
            @ApiResponse(code = 204, message = "Information not found"),
            @ApiResponse(code = 403, message = "Access denied, administrator rights required"),
    })
    @GetMapping(value = "find-all")
    public List<UserEntity> findAllUsers() {
        return userService.getAll();
    }

}