package org.jalvarez.apiservlet.webapp.headers.services;

import jakarta.enterprise.inject.Alternative;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUserName(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(cookie -> "username".equals(cookie.getName()))
                .map(Cookie:: getValue)
                .findAny();
    }
}
