package com.ecommerce.userservices.Service.Impl;

import com.ecommerce.userservices.Config.JwtProvider;
import com.ecommerce.userservices.Entity.User;
import com.ecommerce.userservices.Repository.UserRepository;
import com.ecommerce.userservices.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromJwt(jwt);
        return userRepository.findByEmail(email);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
