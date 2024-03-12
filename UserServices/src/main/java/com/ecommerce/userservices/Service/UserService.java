package com.ecommerce.userservices.Service;

import com.ecommerce.userservices.Entity.User;

import java.util.List;

public interface UserService {
    User getUserProfile(String jwt);

    List<User> getAllUser();
}
