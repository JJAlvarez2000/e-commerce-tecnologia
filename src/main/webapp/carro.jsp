<%@ page contentType="text/html;charset=UTF-8" import="org.jalvarez.apiservlet.webapp.headers.models.*"%>
<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<html>
    <head>
        <title>Carro de compras</title>
    </head>
    <body>
        <h1>Carro de compras</h1>
        <% if (carro == null || carro.getItems().isEmpty()) { %>
            <p>Lo sentimos, no hay productos en el carro</p>
        <% } else { %>
        <form name="formcarro" action="<%=request.getContextPath()%>/carro/actualizar" method="post">
        <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                </tr>
                <% for (ItemCarro item : carro.getItems()) { %>
                    <tr>
                        <td><%= item.getProducto().getId() %></td>
                        <td><%= item.getProducto().getNombre() %></td>
                        <td><%= item.getProducto().getPrecio() %></td>
                        <td><input type="text" size="4" name="cant_<%=item.getProducto().getId()%>" value="<%=item.getCantidad()%>" /></td>
                        <td><%= item.getImporte() %></td>
                        <td><input type="checkbox" value="<%=item.getProducto().getId()%>" name="deleteProductos" />
                        Eliminar productos</td>
                    </tr>
                <% } %>
                <tr>
                    <td colspan="4" style="text-align:right">Total:</td>
                    <td><%= carro.getTotal() %></td>
                </tr>
            </table>
            <a href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>

        <%} %>
        <p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
        <p><a href="<%=request.getContextPath()%>/index.html">Volver al inicio</a></p>
    </body>
</html>
