<%@ page contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Authentication</title>
    <meta charset="utf-8">
    <style type="text/css">
        body {
            margin: 100;
        }

        #parent {
            position: static;
        }

        #block1 {
            position: absolute; /* Абсолютное позиционирование */
            top: 25px; /* Положение от нижнего края */
            left: 400px; /* Положение от правого края */
        }
    </style>
</head>
<body>
<form method="post" action="registration">
<div id="parent">
    <p>Логин (обязательно для заполнения)
    <p>
        <input type="text" id="login" name="login"
               placeholder="Введите имя пользователя" style="width: 308px; height: 30px;
     border-radius:  5px;">
        <br>
    <p>Пароль (обязательно для заполнения)
    <p>
        <input type="text" id="password" name="password"
               placeholder="Введите пароль" style=" width: 308px; height: 30px; border-radius: 5px;">
</div>
<div id="block1">
    <div style="color:rgb(76, 43, 226);">
        <h3>Быстро, бесплатно и <br>
            надежно!</h3>
    </div>
    <div style="color: rgb(0, 81, 255);">
        <ul style="list-style-type: square; padding: 0; margin-left: 20px;">
            <li>Вносите изменения в <br> бронирование</li>
            <li>Настраивайте рассылку <br> предложений</li>
            <li>Выигрывайте время</li>
        </ul>
    </div>
</div>

<p><b>Пользовались ли Вы ранее услугами нашего сервиса <br> (обязательно для заполнения)</b></p>
<p><input  name="new_user" type="radio" value="true"> Нет, я у Вас впервые</p>
<p><input  name="new_user" type="radio" value="false"> Да, я знаком с Вашим сервисом</p>
<p><input type="checkbox" id="distribution" name="distribution" value="true"> Я хочу получать рассылку с <br> эксклюзивными предложениями <br>
</p>
<button onclick="" style="color: white; background: blue;
        width: 200px; height: 30px; border-radius:5px;">Создать мой профиль
</button>
    </form>
<p:if test="${not empty errorMessage}">
    <p style="color: red"> ${errorMessage}</p>
</p:if>
<p>Создавая аккаунт, Вы соглашаетесь с нашими <a href="">Правилами и условиями</a>
    и <a href="">Положением о конфиденциальности.</a></p>
</body>
</html>