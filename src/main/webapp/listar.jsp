<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Listado de productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
        <h1>Listado de productos</h1>
        <c:if test="${username.isPresent()}">
            <div class="alert alert-info">Hola ${username.get()}, bienvenido!</div>
            <a class="btn btn-primary my-3" href="${pageContext.request.contextPath}/productos/form">Crear</a>
        </c:if>
        <table class="table table-hover table-striped">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <c:if test="${username.isPresent()}">
                <th>Precio</th>
                <th>Agregar</th>
                <th>Editar</th>
                <th>Eliminar</th>
                </c:if>
            </tr>
            <c:forEach items="${productos}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.nombre}</td>
                    <td>${p.categoria.nombre}</td>
                    <c:if test="${username.isPresent()}">
                        <td>${p.precio}</td>
                    <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">Agregar al carro</a></td>
                    <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar</a></td>
                    <td><a class="btn btn-sm btn-danger" onclick="return confirm('Esta seguro que desea eliminar?');"
                            href="<%=request.getContextPath()%>/productos/eliminar?id="${p.id}">Eliminar</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <p>${requestScope.mensaje}</p>
        <p>${applicationScope.mensaje}</p>
        </div>
    </body>
</html>
