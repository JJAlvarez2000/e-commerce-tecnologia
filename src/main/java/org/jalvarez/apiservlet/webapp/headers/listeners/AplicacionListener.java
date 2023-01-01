package org.jalvarez.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.jalvarez.apiservlet.webapp.headers.models.Carro;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando la aplicación");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Algun valor global de la app");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicación");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Iniciando una petición request");
        sre.getServletRequest().setAttribute("mensaje", "Algun valor del request");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo el request");
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Iniciando una sesión");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo una sesión");
    }
}
