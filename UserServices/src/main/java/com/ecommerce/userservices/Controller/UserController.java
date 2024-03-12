package com.ecommerce.userservices.Controller;

import com.ecommerce.userservices.Entity.User;
import com.ecommerce.userservices.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) {
        User user = userService.getUserProfile(jwt);
        System.out.println(jwt + " " + user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(@RequestHeader("Authorization") String jwt) {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
