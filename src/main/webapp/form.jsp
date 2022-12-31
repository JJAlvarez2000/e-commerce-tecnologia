<%@ page contentType="text/html;charset=UTF-8" import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
      <title>Formulario Productos</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </head>
    <body>
      <div class="container">

      <h1>Formulario de Productos</h1>
      <form action="${pageContext.request.contextPath}/productos/form" method="post">

        <div class="row mb-2">
          <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
          <div class="col-sm-4">
            <input type="text" name="nombre" id="nombre" value="${producto.nombre}" class="form-control"/>
          </div>
          <c:if test="${errores != null && errores.containsKey('nombre')}">
            <div style="color: red">${errores.nombre}></div>
            </c:if>
        </div>

        <div class="row mb-2">
          <label for="precio" class="col-form-label col-sm-2">Precio</label>
          <div class="col-sm-4">
            <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio : ""}" class="form-control"/>
          </div>
          <c:if test="${errores != null && !empty errores.precio}">
            <div style="color: red">${errores.precio}></div>
          </c:if>
        </div>

        <div class="row mb-2">
          <label for="sku" class="col-form-label col-sm-2">Sku</label>
          <div class="col-sm-4">
            <input type="text" name="sku" id="sku" value="${producto.sku}"class="form-control"/>
          </div>
          <c:if test="${errores != null && !empty errores.sku}">
            <div style="color: red">${errores.sku}></div>
          </c:if>
        </div>

        <div class="row mb-2">
          <label for="fecha_registro" class="col-form-label col-sm-2">Fecha de registro</label>
          <div class="col-sm-4">
            <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}"class="form-control"/>
          </div>
          <c:if test="${errores != null && !empty errores.fecha_registro}">
            <div style="color: red">${errores.fecha_registro}></div>
          </c:if>
        </div>

        <div class="row mb-2">
          <label for="categoria" class="c class=col-sm-4ol-form-label col-sm-2">Categoria</label>
          <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select"/>
                <option value="">>-- Seleccionar --<</option>
            <c:forEach items="${categorias}" var="c">
                <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? "selected" : ""}>${c.nombre}</option>
            </c:forEach>
            </select>
          </div>
          <c:if test="${errores != null && !empty errores.categoria}">
            <div style="color: red">${errores.categoria}></div>
          </c:if>
        </div>

        <div class="row mb-2">
          <div>
          <input class="btn btn-primary" type="submit" value="${producto.id != null && producto.id > 0 ? "Editar" : "Crear" }"/>
          </div>
        </div>
        <input type="hidden" name="id" value="${producto.id}">
      </form>
      </div>
    </body>
</html>
