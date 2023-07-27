package com.example.userjoinlogin.controller;

import com.example.userjoinlogin.dto.UserDto;
import com.example.userjoinlogin.dto.request.UserJoinRequest;
import com.example.userjoinlogin.dto.request.UserLoginRequest;
import com.example.userjoinlogin.dto.response.UserJoinResponse;
import com.example.userjoinlogin.dto.response.UserLoginResponse;
import com.example.userjoinlogin.exception.Response;
import com.example.userjoinlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserDto userDto = userService.join(request);
        UserJoinResponse userJoinResponse = UserJoinResponse.fromUserDto(userDto);
        return Response.success(userJoinResponse);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
