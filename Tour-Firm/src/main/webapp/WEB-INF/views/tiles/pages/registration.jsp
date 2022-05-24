<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<body>
<div class="container">

    <link href='http://fonts.googleapis.com/css?family=Lobster|Pacifico:400,700,300|Roboto:400,100,100italic,300,300italic,400italic,500italic,500' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,500,600,700,300' rel='stylesheet' type='text/css'>

    <style>
        <%@include file='/css/registration.css' %>
    </style>

    <div class="errorMessage">
        <p>${requestScope.get("error")}</p>
    </div>

    <div class="main">
        <div  class="wrap">
            <div class="Regisration">
                <div class="Regisration-head">
                    <h2><span></span>Register</h2>
                </div>
                <form method="post">
                    <input name="name" style="margin-bottom: 10px; margin-right: 5px;" type="text" placeholder="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <input name="surname" style="margin-bottom: 10px;" type="text" placeholder="Surname" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <input name="phone" style="margin-bottom: 10px; margin-right: 5px;" type="text" placeholder="Your phone number" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <input name="login" style="margin-bottom: 10px;" type="text" placeholder="Login" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <input name="password" style="margin-right: 5px;" type="password" placeholder="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <input name="password1" type="password" placeholder="Confirm your password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
                    <div class="Remember-me">
                        <div class="submit">
                            <input style="margin-top: 20px; color: #ffffff; font-size: 13px;" type="submit" class="btn btn-primary" value="Sign Me Up" >
                        </div>
                        <div class="clear"> </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>