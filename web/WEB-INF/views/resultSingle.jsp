<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 23.12.2018
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="resources/css/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>PHONEBOOK</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Result</h2>
        </div>

        <div class="w3-panel w3-light-green w3-display-container w3-card-4 w3-round">
            <h5>Result - operation "${operationName}"  complete</h5>
            <div class="w3-container w3-light-blue">

                    <h6>
                            ${user.toString()}

                    </h6>

            </div>
        </div>
    </div>
</div>


<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick='history.back()'>Back</button>
    <button class="w3-btn w3-round-large" onclick="location.href='../..'">Back to main</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/list'">Back to search</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/add'">Back to add</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/update'">Back to update</button>
    <button class="w3-btn w3-round-large" onclick="location.href='/delete'">Back to delete</button>
</div>
</body>
</html>
