package io.mmy.todoapp.controller.auth;

import io.mmy.todoapp.dto.JwtResponseDto;
import io.mmy.todoapp.dto.LoginInfo;
import io.mmy.todoapp.dto.UserDto;
import io.mmy.todoapp.jwt.TokenManagerImpl;
import io.mmy.todoapp.service.auth.AuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserDetailController {
    @Autowired
    private TokenManagerImpl tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetailService authUserDetailService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDto> signin(@RequestBody LoginInfo loginInfo) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));

            return ResponseEntity.ok(
                    new JwtResponseDto(
                            loginInfo.getUsername(),
                            tokenManager.generateToken(loginInfo.getUsername()
                            )
                    ));
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){

        return ResponseEntity.ok(authUserDetailService.registerUser(userDto).toString());
    }
}
