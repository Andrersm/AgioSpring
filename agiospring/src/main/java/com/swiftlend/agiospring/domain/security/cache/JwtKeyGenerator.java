package com.swiftlend.agiospring.domain.security.cache;

import com.swiftlend.agiospring.domain.security.model.User;
import com.swiftlend.agiospring.domain.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("jwtKeyGenerator")
@RequiredArgsConstructor
public class JwtKeyGenerator implements KeyGenerator {

    private final TokenService tokenService;


    @Override
    public Object generate(Object target, Method method, Object... params) {
        User user = tokenService.getUserFromToken();
        return user.getId();
    }
}
