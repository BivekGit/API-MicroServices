package com.ecommerce.userservices.Service.Impl;


import com.ecommerce.userservices.Entity.User;
import com.ecommerce.userservices.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            return new org.springframework.security.core.userdetails
                    .User(user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);

    }
}
