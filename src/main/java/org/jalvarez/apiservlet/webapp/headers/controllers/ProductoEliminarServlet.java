package org.jalvarez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoService;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);

        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                resp.sendError(404, "No existe el producto en la base de datos");
            }
        } else {
            resp.sendError(404, "Error, el ID es nulo, se debe enviar como parametro en la URl");
        }
    }
}
