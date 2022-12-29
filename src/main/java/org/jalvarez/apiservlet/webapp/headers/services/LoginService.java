package org.jalvarez.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    Optional<String> getUserName(HttpServletRequest req);
}
