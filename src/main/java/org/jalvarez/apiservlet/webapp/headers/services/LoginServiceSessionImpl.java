package org.jalvarez.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@ApplicationScoped
public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUserName(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        if(userName != null) {
            return Optional.of(userName);
        }
        return Optional.empty();
    }
}

