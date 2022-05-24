<%--
  Created by IntelliJ IDEA.
  User: Anonym
  Date: 12.12.2019
  Time: 3:57
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<body>
<div class="custom-padding">
    <nav>
        <div class="logo">TourFirm</div>

        <ul class="menu-area">

            <c:if test="${session.equals('true')}">
                <li><a href="index?session=true">Home</a></li>
                <li><a href="profile?session=true">Cabinet</a></li>
                <li><a href="logout">Logout</a></li>
                <c:if test="${isAdmin.equals('true')}">
                    <li><a href="statistics">Statistics</a></li>
                </c:if>
                <li><a href="hotels?id=all">Country</a>
                    <ul class="listG">
                        <li><a href="hotels?id=Germany">Germany</a></li>
                        <li><a href="hotels?id=Iceland">Iceland</a></li>
                        <li><a href="hotels?id=Poland">Poland</a></li>
                        <li><a href="hotels?id=Canada">Canada</a></li>
                        <li><a href="hotels?id=North Korea">North Korea</a></li>
                        <li><a href="hotels?id=USA">USA</a></li>
                        <li><a href="hotels?id=Belgium">Belgium</a></li>
                        <li><a href="hotels?id=Ukraine">Ukraine</a></li>
                        <li><a href="hotels?id=Japan">Japan</a></li>
                    </ul>
                </li>
                <li><a href="hotel?id=all">Hotel</a></li>
            </c:if>
            <c:if test="${!session.equals('true')}">
                <li><a href="index?session=true">Home</a></li>
                <li><a href="login">Sign in</a></li>
                <li><a href="register">Sign up</a></li>
            </c:if>

        </ul>
    </nav>
</div>

</body>
</html>
