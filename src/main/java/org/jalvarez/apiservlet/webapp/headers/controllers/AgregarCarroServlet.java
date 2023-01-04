package org.jalvarez.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jalvarez.apiservlet.webapp.headers.annotations.ProductoServicePrincipal;
import org.jalvarez.apiservlet.webapp.headers.models.Carro;
import org.jalvarez.apiservlet.webapp.headers.models.ItemCarro;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
//        Connection conn = (Connection) req.getAttribute("conn");
//        ProductoService service = new ProductoServiceJDBCImpl(conn);
        Optional<Producto> producto = service.porId(id);
        if(producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            // ahora al carro lo inyectamos por CDI
//            HttpSession session = req.getSession();
//            CarroCompra carro = (CarroCompra) session.getAttribute("carro");
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
