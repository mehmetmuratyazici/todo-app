package io.mmy.todoapp.controller.auth;

import io.mmy.todoapp.LoginInfo;
import io.mmy.todoapp.jwt.TokenManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private TokenManagerImpl tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginInfo loginInfo) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));

            return ResponseEntity.ok(tokenManager.generateToken(loginInfo.getUsername()));
        } catch (Exception e) {
            throw e;
        }
    }
}
