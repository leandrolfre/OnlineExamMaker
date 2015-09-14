<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>

<head>
    <title>Online Test Exam Maker</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>

<body onload='document.f.username.focus();'>
<h1>Welcome to Online Test Exam Maker</h1>
<h2><c:out value="${exam.getName()} - ${exam.getDescription()}" /></h2>


<h3>Login with Username and Password</h3>

<c:if test="${error == true}">
    <b class="error">Invalid login or password.</b>
</c:if>
<form name='f' action='/onlineTestMaker/login' method='POST'>
    <table>
        <tr><td>User:</td><td>
            <input type='text' name='username' value=''></td></tr>
        <tr><td>Password:</td>
            <td><input type='password' name='password'/></td></tr>
        <tr><td colspan='2'>
            <input name="submit" type="submit" value="Login"/></td></tr>
    </table>
</form>


</body>

</html>
