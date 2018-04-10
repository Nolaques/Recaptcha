<%--
  Created by IntelliJ IDEA.
  User: belyak
  Date: 08.04.2018
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- reCAPTCHA Library -->
    <script src = 'https://www.google.com/recaptcha/api.js?hl=en'></script>
</head>
<body>

<h3>Login:</h3>

<p style="color:red;">${errorString}</p>

<form name="loginForm" method="post" action="doLogin">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>

    <!-- reCAPTCHA -->
    <div class="g-recaptcha"
         data-sitekey="6Ldk1lEUAAAAAONem-49XNMh0033Zl-VfEm9434Q"></div>

    <input type="submit" value="Submit"/>

</form>
<p style="color:blue;">User Name: tom, Password: tom001</p>

</body>
</html>
