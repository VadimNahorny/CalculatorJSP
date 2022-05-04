<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
    <style>
        <%@ include file='/css/calculator.css' %>
        <%@ include file='/css/bootstrap.min.css'%>
    </style>
</head>
<body class="body">


<%--bellow javascript functions--%>

<script>
    var fullString = null;
    var OldFullString = null;
    var newSymbol = null;

    function add(a) {
        OldFullString = document.getElementById("hidden").value
        newSymbol = document.getElementById(a).value;

        if (!invalidFirstOperation(OldFullString, newSymbol) &&
            !invalidEnterOperand(OldFullString, newSymbol) &&
            !invalidEnterBracket(OldFullString, newSymbol) &&
            !invalidEnterPoint(OldFullString, newSymbol) &&
            !invalidEnterZero(OldFullString, newSymbol)) {
            fullString = OldFullString + newSymbol;
            document.getElementById("field").innerHTML = fullString;
            document.getElementById("hidden").value = fullString;
        }
    }

    function backspace() {
        OldFullString = document.getElementById("hidden").value
        if (OldFullString !== "") {
            OldFullString = document.getElementById("hidden").value
            fullString = OldFullString.substring(0, OldFullString.length - 1);
            document.getElementById("field").innerHTML = fullString;
            document.getElementById("hidden").value = fullString;
        }
    }

    function invalidFirstOperation(OldFullString, newSymbol) {
        return ((OldFullString === "") && ((newSymbol === "+") || (newSymbol === "-") || (newSymbol === "*") ||
            (newSymbol === "/") || (newSymbol === ".")));
    }

    function invalidEnterOperand(OldFullString, newSymbol) {
        return ((OldFullString !== "") && (((newSymbol === "+") || (newSymbol === "-") || (newSymbol === "*") || (newSymbol === "/")) &&
            ((OldFullString[OldFullString.length - 1] === "+") || (OldFullString[OldFullString.length - 1] === "-") ||
                (OldFullString[OldFullString.length - 1] === "*") || (OldFullString[OldFullString.length - 1] === "/") ||
                (OldFullString[OldFullString.length - 1] === "."))));
    }

    function invalidEnterBracket(OldFullString, newSymbol) {
        if (((OldFullString === "") && (newSymbol === ")")) ||

            ((OldFullString[OldFullString.length - 1] === "(") && (newSymbol === ")")) ||

            ((OldFullString[OldFullString.length - 1] === ")") && (newSymbol === "(")) ||

            (((OldFullString[OldFullString.length - 1] === "*") || (OldFullString[OldFullString.length - 1] === "/") ||
                (OldFullString[OldFullString.length - 1] === "-") || (OldFullString[OldFullString.length - 1] === "+") ||
                (OldFullString[OldFullString.length - 1] === ".")) && (newSymbol === ")")) ||

            (OldFullString[OldFullString.length - 1] === ".") && (newSymbol === "(") ||

            ((OldFullString[OldFullString.length - 1] === "(") && ((newSymbol === "*") || (newSymbol === "/") || (newSymbol === "+")))) {
            return true;
        }
        if ((OldFullString !== "") && (((OldFullString[OldFullString.length - 1].match(/\d/) !== null) && (newSymbol === "(")) ||
            ((OldFullString[OldFullString.length - 1] === ")") && (newSymbol.match(/\d/) !== null)))) {
            return true;
        }
        if (newSymbol === ")" && !OldFullString.includes('(')) {
            return true;
        }

        if (newSymbol === ")" && OldFullString.includes('(')) {
            var countClosedBracket = 0;
            var countOpenedBracket = 0;
            var step = null;
            for (step = (OldFullString.length - 1); step >= 0; step--) {
                if (OldFullString[step] === ")") countClosedBracket++;
                if (OldFullString[step] === "(") break;
            }
            for (; step >= 0; step--) {
                if (OldFullString[step] === "(") countOpenedBracket++;
                if (OldFullString[step] === ")") break;
            }
            if (countClosedBracket >= countOpenedBracket) {
                return true;
            }
        }
        return false;
    }

    function invalidEnterPoint(OldFullString, newSymbol) {
        var a = false;
        if ((OldFullString !== "") && (newSymbol === ".")) {
            if (OldFullString[OldFullString.length - 1].match(/\d/) === null) return true;
            for (step = (OldFullString.length - 1); step >= 0; step--) {
                if (OldFullString[step] === "+" || OldFullString[step] === "-" || OldFullString[step] === "/" || OldFullString[step] === "*" ||
                    OldFullString[step] === "(") {
                    return a
                }
                if (OldFullString[step] === ".") {
                    a = true;
                    return a;
                }
            }
        }
        return a;
    }

    function invalidEnterZero(OldFullString, newSymbol) {
        if (OldFullString !== "" && newSymbol === "0") {
            if (OldFullString.length === 1 && OldFullString === "0") return true;
            if (OldFullString.length > 1 && OldFullString[OldFullString.length - 1] === "0" &&
                (OldFullString[OldFullString.length - 2] === "+" || OldFullString[OldFullString.length - 2] === "-" ||
                    OldFullString[OldFullString.length - 2] === "/" || OldFullString[OldFullString.length - 2] === "*" ||
                    OldFullString[OldFullString.length - 2] === "(")) return true;
        }
        return false;
    }


    function clearField() {
        document.getElementById("field").innerHTML = null;
        document.getElementById("hidden").value = null;
    }


</script>

<h1> Calculator </h1>

<%-- bellow form, result button, field with expression, error expressions--%>

<form method="post" action="calculator" id="calculator">

    <p:if test="${not empty errorMessage}">
        <p style="color: red"> ${errorMessage}</p>
    </p:if>

    <c:choose>
        <c:when test="${not empty mathExpression}">
            <p id="field">${mathExpression}</p>
            <input type="hidden" name="hidden" id="hidden" value="${mathExpression}">
        </c:when>
        <c:otherwise>
            <p id="field"></p>
            <input type="hidden" name="hidden" id="hidden" value="">
        </c:otherwise>
    </c:choose>
</form>

<%--bellow figure and operand buttons--%>

<div class="buttons" id="buttons">
    <table>
        <tr>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="plus" value="+">+
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="sub" value="-">-
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="mult" value="*">*
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="separ" value="/">/
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="1" value="1">1
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="2" value="2">2
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="3" value="3">3
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="4" value="4">4
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="5" value="5">5
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="6" value="6">6
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="7" value="7">7
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="8" value="8">8
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="9" value="9">9
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="0" value="0">0
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="leftBracket" value="(">(
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="rightBracket" value=")">)
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn btn-primary" type="submit" onclick="add(this.id)" id="point" value=".">.
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="clearField()" id="cl">CE
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" onclick="backspace()" id="bs"><=
                </button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" form="calculator">=
                </button>
            </td>
        </tr>

    </table>
</div>

<label class="logout">
    <tr>
        <a href="logout">Выйти</a>
    </tr>
</label>

<label class="previousOperations" id="previousOperations">
    <p:if test="${not empty previousOperations}">
        <h5>Ваши предыдущие операции:</h5>
    </p:if>

    <table class="previousOperation">
        <c:forEach var="previousOperation" items="${previousOperations}">
            <tr>
                <td><p>${previousOperation}</p></td>
            </tr>
        </c:forEach>
    </table>
</label>
<br>
<br>


</body>
</html>
