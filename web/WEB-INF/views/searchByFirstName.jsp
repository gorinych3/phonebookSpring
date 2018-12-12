<%@ page import="ru.egor.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 24.10.2018
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search by First Name</title>
    <%--<link rel="stylesheet" href="../css/w3.css">--%>
    <link rel="stylesheet" href="resources/css/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>PHONEBOOK</h1>
</div>

<div class="w3-card-4">
    <div class="w3-container w3-center w3-light-blue">
        <h2>Search by First Name</h2>
    </div>
</div>

<div class="w3-container w3-padding">
    <%

        List<User> users = (List<User>) request.getAttribute("users");

        if (users != null && !users.isEmpty()) {
            out.println("<ul class=\"w3-ul\">");
            for (User us : users) {
                out.println("<li class=\"w3-hover-sand\">" + us.toString() + "</li>");
            }
            out.println("</ul>");

        } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                "   <h5>Input current first name </h5>\n" +
                "</div>");
    %>
    <div class="w3-card-4">

        <form method="post" action="${pageContext.request.contextPath}/searchByFirstName" class="w3-selection w3-light-grey w3-padding">

            <label>First Name:
                <input type="text" name="f_name" value="${requestScope.get("f_name")}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%">Поле обязательное для заполнения<br />
            </label>

            <button type="submit" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom">Принять</button>

        </form>

    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../..'">Back to main</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/list'">Back to search</button>
</div>
</body>
</html>