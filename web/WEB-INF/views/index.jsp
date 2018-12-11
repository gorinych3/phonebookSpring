<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 04.12.2018
  Time: 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PHONEBOOK</title>
    <%--<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">--%>
    <link rel="stylesheet" href="resources/css/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>PHONEBOOK</h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/list'">Users List</button>
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/add'">Add List</button>
        <button class="w3-btn w3-hover-orange w3-round-large" onclick="location.href='/updateList'">Update List</button>
        <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='/delete'">Delete List</button>
    </div>
</div>
</body>
</html>
