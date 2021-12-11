package io.mmy.todoapp.jwt;

import org.springframework.stereotype.Service;

public interface TokenManager {

    String generateToken(String username);

    boolean tokenValidate(String token);

    String getUsernameByToken(String token);

    boolean isExpired(String token);


}
