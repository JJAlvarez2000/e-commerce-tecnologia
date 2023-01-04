package org.jalvarez.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import org.jalvarez.apiservlet.webapp.headers.annotations.MySqlConn;
import org.jalvarez.apiservlet.webapp.headers.exceptions.ServiceJDBCException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    /* @Inject
    @MySqlConn
    private Connection conn;
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // try (Connection conn = ConexionJdbsDS.getConnection()) {
        /* try {
            Connection connRequest = this.conn;
            System.out.println("Conexión abierta");
            if(connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }
         */
            try {
                // con esto queda disponible para todo el request y podemos usarlo en el servlet/jsp
                //servletRequest.setAttribute("conn", connRequest);
                filterChain.doFilter(servletRequest, servletResponse);
                // connRequest.commit();
            } catch (ServiceJDBCException e) {
                // connRequest.rollback();
                ((HttpServletResponse) servletResponse).sendError(500, "Error en la transacción");
                e.printStackTrace();
            }
        /* } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
         */
    }
}
