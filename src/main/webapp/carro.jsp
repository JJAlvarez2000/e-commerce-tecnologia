<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Carro de compras</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
        <h1>Carro de compras</h1>
        <c:choose>
        <c:when test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
            <div class="alert alert-warning"> Lo sentimos, no hay productos en el carro</div>
        </c:when>
        <c:otherwise>
        <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
        <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th>Borrar</th>
                </tr>
            <c:forEach items="${carro.items}" var="item">
                    <tr>
                        <td>${item.producto.id}</td>
                        <td>${item.producto.nombre}</td>
                        <td>${item.producto.precio}</td>
                        <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}" /></td>
                        <td>${item.importe}</td>
                        <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos"/></td>
                    </tr>
            </c:forEach>
                <tr>
                    <td colspan="5" style="text-align:right">Total:</td>
                    <td>${carro.total}</td>
                </tr>
            </table>
            <a class="btn btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>
        </c:otherwise>
        </c:choose>
        <div class="my-2">
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/index.html">Volver al inicio</a>
            <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>
        </div>
        </div>
    </body>
</html>
