<%@ page contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Authentication</title>
    <meta charset="utf-8">
</head>
<body>

<form method="post" action="auth">
<h2> Страница входа в сервис</h2>
 <input type="text" id="login" name ="login" value="${login}"
       placeholder="Введите имя пользователя"  style="width: 308px; height: 30px;
        border-radius:  5px;">
<br>  <br>
<input type="text" id="password" name ="password" value="${password}"
       placeholder="Введите пароль" style=" width: 308px; height: 30px; border-radius: 5px;">
<br>  <br>
<button style="color: white; background: blue;
        width: 200px; height: 30px; border-radius:5px;">Войти</button>
</form>
    <p:if test="${not empty errorMessage}">
        <p style="color: red"> ${errorMessage}</p>
    </p:if>
<p> Вы еще не являетесь пользователем нашего сервиса?
    <a href="reg">Зарегистрируйтесь прямо сейчас!</a>
</p>
</body>
</html>
