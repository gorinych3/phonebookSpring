<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 24.10.2018
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="resources/css/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>PHONEBOOK</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Add pet</h2>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/addPet" class="w3-selection w3-light-grey w3-padding">
            <label>ID:
                <input type="text" name="id" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%">Поле обязательное для заполнения<br />
            </label>

            <label>Домашний питомец:
                <input type="text" name="petType" value="unknown" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>

            <label>Имя питомца:
                <input type="text" name="petName" value="unknown" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Принять</button>

        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../..'">Back to main</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/add'">Back to add</button>
</div>
</body>
</html>
