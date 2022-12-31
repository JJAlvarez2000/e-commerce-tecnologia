package org.jalvarez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jalvarez.apiservlet.webapp.headers.models.Categoria;
import org.jalvarez.apiservlet.webapp.headers.models.Producto;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoService;
import org.jalvarez.apiservlet.webapp.headers.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
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
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()) {
                producto = o.get();
            }
        }
        req.setAttribute("categorias", service.listarCategoria());
        req.setAttribute("producto", producto);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        String nombre = req.getParameter("nombre");
        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        }
        catch (NumberFormatException e) {
            precio = 0;
        }
        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(req.getParameter("categoria"));
        }
        catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es obligatorio");
        } else if (sku.length() > 10){
            errores.put("sku", "El sku debe tener como maximo 10 caracteres");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es obligatoria");
        }
        if (precio.equals(0)) {
            errores.put("precio", "El precio es obligatorio");
        }

        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoria es obligatoria");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setFechaRegistro(fecha);

        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);

        }
    }
}
