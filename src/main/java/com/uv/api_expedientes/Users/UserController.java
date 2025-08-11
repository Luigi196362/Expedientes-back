package com.uv.api_expedientes.Users;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Users.dtos.AllUsersDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<AllUsersDto> getAllActiveUsers() {
        return userService.getUsers();
    }

    @PostMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable("id") int id) {
        return userService.deactivateUser(id);
    }

    @PostMapping("/reactivate/{id}")
    public String reactivateUser(@PathVariable("id") int id) {
        return userService.reactivateUser(id);
    }
}
