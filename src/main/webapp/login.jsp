<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Formulario de login</title>
</head>
<body>
<h1>Iniciar sesión</h1>
<form action="/webapp-session/login" method="post">
  <div>
    <label for="username">Username</label>
    <div>
      <input type="text" name="username" id="username">
    </div>
  </div>
  <div>
    <label for="password">Password</label>
    <div>
      <input type="password" name="password" id="password">
    </div>
  </div>
  <div>
    <input type="submit" value="Login">
  </div>
</form>
</body>
</html>