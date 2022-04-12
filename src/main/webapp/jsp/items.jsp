<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Goods</title>
    <style>
        <%@ include file='/css/order.css' %>
        <%@ include file='/css/bootstrap.min.css'%>
    </style>
</head>
<body class="body">

<header class="header">
    Выберите товар и добавьте его в корзину
</header>


    <div>
<table class="goods">
    <c:forEach var="good" items="${goods}">
        <tr>
            <td><img src="${good.image}"></td>
            <td><p>${good.name}</p></td>
            <td>
                <button type="submit" onclick="myFunc(this.id)" id="${good.name}" name="TVbutton" style="color: white; background: blue;
             width: 200px; height: 30px; border-radius:5px;" value="${good.name}">Добавить товар в корзину
                </button>
            </td>
            <td>
                <button type="submit" onclick=" deleteGoods(this.id)" id="${good.name}" name="TVbutton" style="color: white; background: blue;
             width: 200px; height: 30px; border-radius:5px;" value="${good.name}">Удалить товары категории из корзины
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

<script>
    var goods = []; // создаем переменную, содержащую массив строковых данных
    var newGoods; // инициализируем пустую переменную

    function myFunc(a) {
        newGoods = document.getElementById(a).value; // находим и записываем в переменную значение первого элемента input в документе
        goods.push(newGoods); // добавляем значение переменной в конец массива
        document.getElementById("goods").innerHTML = goods; // добавляем новое содержимое переменной в элемент с глобальным атрибутом
        document.getElementById("goodsServ").value = goods;
    }

    function clearBasket() {
        goods = [];
        document.getElementById("goods").innerHTML = goods; // добавляем новое содержимое переменной в элемент с глобальным атрибутом
    }

    function deleteGoods(a) {
        goods = goods.filter(function (f) {
            return f !== a
        });
        document.getElementById("goods").innerHTML = goods;
    }
</script>
    <button type="submit" onclick="clearBasket()" style="color: white; background: blue;
             width: 200px; height: 30px; border-radius:5px;">Очистить корзину
    </button>
<input type="hidden" form="itemsServ" name="goodsServ"  id = "goodsServ">

<div class="basket">
<p> Ваша корзина: </p>
<p id="goods" name="goods"></p>
</div>

    <form method="post" action="itemsServ" id="itemsServ">
        <h1> Форма для оформления заказа </h1>

        <p:if test="${not empty errorMessage}">
            <p style="color: red"> ${errorMessage}</p>
        </p:if>

        <p>Ведите имя, по которому к Вам можно обращаться
        </p>
        <input type="text" name="name"
               placeholder="Введите Ваше имя" style="width: 308px; height: 30px;
     border-radius:  5px;">
        <br>
        <p>Введите номер телефона, по которому наш менеджер сможет с Вами связаться
        </p>

        <input type="text" name="phone"
               placeholder="Введите номер телефона" style=" width: 308px; height: 30px; border-radius: 5px;">
        <button type="submit" style="color: white; background: blue;
             width: 200px; height: 30px; border-radius:5px;">Отправить заказ менеджеру
        </button>
    </form>
</body>
</html>
