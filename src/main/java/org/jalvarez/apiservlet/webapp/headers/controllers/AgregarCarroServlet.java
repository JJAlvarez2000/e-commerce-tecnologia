package org.jalvarez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jalvarez.apiservlet.webapp.headers.models.Carro;
import org.jalvarez.apiservlet.webapp.headers.models.ItemCarro;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoService;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoServiceImpl;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        Optional<Producto> producto = service.porId(id);
        if(producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
