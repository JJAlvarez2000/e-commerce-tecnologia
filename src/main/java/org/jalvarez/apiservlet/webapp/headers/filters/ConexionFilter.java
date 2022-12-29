package org.jalvarez.apiservlet.webapp.headers.filters;

import org.jalvarez.apiservlet.webapp.headers.exceptions.ServiceJDBCException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.jalvarez.apiservlet.webapp.headers.utils.ConexionJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection conn = ConexionJDBC.getConnection()) {
            System.out.println("Conexión abierta");
            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                // con esto queda disponible para todo el request y podemos usarlo en el servlet/jsp
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServiceJDBCException e) {
                conn.rollback();
                ((HttpServletResponse) servletResponse).sendError(500, "Error en la transacción");
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
    }
}
