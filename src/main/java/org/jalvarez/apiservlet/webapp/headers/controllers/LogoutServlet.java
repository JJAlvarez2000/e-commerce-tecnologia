package org.jalvarez.apiservlet.webapp.headers.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.jalvarez.apiservlet.webapp.headers.services.LoginService;
import org.jalvarez.apiservlet.webapp.headers.services.LoginServiceCookieImpl;
import org.jalvarez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/logout.html", "/logout"})
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUserName(req);
        if(username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}