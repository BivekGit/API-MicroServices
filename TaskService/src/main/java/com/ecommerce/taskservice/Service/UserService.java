package com.ecommerce.taskservice.Service;

import com.ecommerce.taskservice.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081")
public interface UserService {
    @GetMapping("/api/users/profile")
    UserDto getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception;
}
