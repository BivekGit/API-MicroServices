package com.ecommerce.userservices.Controller;

import com.ecommerce.userservices.Config.JwtProvider;
import com.ecommerce.userservices.Request.LoginRequest;
import com.ecommerce.userservices.Entity.User;
import com.ecommerce.userservices.Repository.UserRepository;
import com.ecommerce.userservices.Response.AuthResponse;
import com.ecommerce.userservices.Service.Impl.CustomUserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    public final UserRepository userRepository;
    public final CustomUserServiceImplementation customUserServiceImplementation;
    public final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole();


        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            throw new Exception("Email already exist");
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setFullName(fullName);
            newUser.setRole(role);
            User savedUser = userRepository.save(newUser);


            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = JwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(token);
            authResponse.setMessage("User created successfully");
            authResponse.setStatus(true);
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

        }

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(email + " ------ " + password);

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("User logged in successfully");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(email);

        System.out.println("SignIn UserDetails " + userDetails);

        if (userDetails == null) {
            System.out.println("SignIn UserDetails " + null);
            throw new BadCredentialsException("Invalid Credentials");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }

}
