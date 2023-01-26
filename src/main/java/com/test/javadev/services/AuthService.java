package com.test.javadev.services;

import com.sun.istack.NotNull;
import com.test.javadev.entities.UserEntity;
import com.test.javadev.requests.AuthRequest;
import com.test.javadev.shared.SharedJwt;
import com.test.javadev.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SharedJwt sharedJwt;

    public AuthService(UserService userRepository, PasswordEncoder passwordEncoder, SharedJwt sharedJwt) {
        this.userService = userRepository;
        this.sharedJwt = sharedJwt;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<?> login(AuthRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return Response.errorResponse(HttpStatus.BAD_REQUEST, "username & password cannot be empty");
        }

        UserEntity userEntity = userService.findByUsername(request.getUsername());

        if (userEntity == null) {
            String message = "User not found";
            return Response.errorResponse(HttpStatus.NOT_FOUND, message);
        }

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            String message = "Password doesn't match";
            return Response.errorResponse(HttpStatus.BAD_REQUEST, message);
        }

        try {
            return getTokenResponse(request.getUsername(), request.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace();

            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> register(AuthRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return Response.errorResponse(HttpStatus.BAD_REQUEST, "username & password cannot be empty");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity userEntity = new UserEntity(
                null,
                request.getUsername(),
                hashedPassword,
                null
        );

        try {
            userService.save(userEntity);

            return getTokenResponse(request.getUsername(), request.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace();

            return Response.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Username has been registered");
        }
    }

    private @NotNull
    ResponseEntity<?> getTokenResponse(String username, String password) {
        User user = getUserFromEntity(username, password);
        String token = sharedJwt.generateToken(user);

        HashMap<String, Object> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    private @NotNull User getUserFromEntity(String username, String password) {
        return new User(username, password, new ArrayList<>());
    }
}
