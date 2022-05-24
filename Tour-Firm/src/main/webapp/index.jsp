<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<%--<nav class="navbar navbar-inverse navbar-fixed-top">--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="navbar-header">--%>
<%--            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"--%>
<%--                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">--%>
<%--                <span class="sr-only">Toggle navigation</span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--            </button>--%>
<%--            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>--%>
<%--        </div>--%>

<%--        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">--%>
<%--            <ul class="nav navbar-nav" id="navbar-nav">--%>
<%--                <li><a href="${pageContext.request.contextPath}/hotelInfo">Hotel</a></li>--%>
<%--                <c:if test="${!session.equals('true')}">--%>
<%--                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>--%>
<%--                </c:if>--%>
<%--                <c:if test="${session.equals('true')}">--%>
<%--                    <li><a href="${pageContext.request.contextPath}/user">Cabinet</a></li>--%>
<%--                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>--%>
<%--                </c:if>--%>
<%--            </ul>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>TourFirm</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            outline: 0;
            box-sizing: border-box;
        }
        body {
            background-image: url(https://images.unsplash.com/photo-1500021804447-2ca2eaaaabeb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80);
            -webkit-background-size:cover;
            background-size:cover;
            background-position: center center;
            height: 100vh;
        }
        .menu-area li a {
            text-decoration: none;
        }

        .menu-area li {
            list-style-type: none;
        }

        .custom-padding{
            padding-top: 25px;
        }

        nav {
            position: relative;
            width: calc(100% - 60px);
            margin: 0 auto;
            padding: 10px 0;
            background: #333;
            z-index: 1;
            text-align: right;
            padding-right: 2%;
        }

        .logo {
            width: 15%;
            float: left;
            text-transform: uppercase;
            color: #fff;
            font-size: 25px;
            text-align: left;
            padding-left: 2%;
        }

        .menu-area li {
            display: inline-block;
        }

        .menu-area a {
            color: #fff;
            font-weight: 300;
            letter-spacing: 1px;
            text-transform: uppercase;
            display: block;
            padding: 0 25px;
            font-size: 14px;
            line-height: 30px;
            position: relative;
            z-index: 1;
        }
        .menu-area a:hover {
            background: cornflowerblue;
            color: #fff;
        }

        .menu-area a:hover:after {
            transform: translateY(-50%) rotate(-35deg);
        }

        nav:before {
            position: absolute;
            content: '';
            border-top: 10px solid #333;
            border-right: 10px solid #333;
            border-left: 10px solid transparent;
            border-bottom: 10px solid transparent;
            top: 100%;
            left: 0;
        }

        nav:after {
            position: absolute;
            content: '';
            border-top: 10px solid #333;
            border-left: 10px solid #333;
            border-right: 10px solid transparent;
            border-bottom: 10px solid transparent;
            top: 100%;
            right: 0;
        }

        /*sub menu*/
        li ul {
            position: absolute;
            min-width: 150px;
            display: none;
        }

        li > ul li {
            border: 1px solid #333333;
        }

        li > ul li a {
            padding: 10px;
            text-transform: none;
            background: #333333;
        }

        li > ul li ul {
            position: absolute;
            right: -150px;
            top: 0;
        }

        li:hover > ul {
            display: block;
        }

        .footer {
            background-color: #333333;
            color: #fff;
            display: block;
            flex: 0 0 auto;
            margin-top: 5px;
        }
         .mySlides {display:none}

        .slide-show{
            position: center;
            margin-top: 20px;
        }


    </style>
</head>
<body>
<div class="custom-padding">
    <nav>
        <div class="logo">TourFirm</div>

        <ul class="menu-area">

            <c:if test="${session.equals('true')}">
                <li><a href="index?session=true">Home</a></li>
                <li><a href="profile?session=true">Cabinet</a></li>
                <li><a href="logout">Logout</a></li>
                <li><a href="bookings?session=true">Bookings</a></li>
                    <c:if test="${isAdmin.equals('true')}">
                        <li><a href="stats?id=all">Statistic</a>
                            <ul class="listG">
                                <li><a href="userstats">Users</a></li>
                                <li><a href="hotelstats">Hotels</a></li>
                            </ul>
                        </li>
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
            <li><a href="hotels?id=all">Hotel</a></li>
            </c:if>
           <c:if test="${!session.equals('true')}">
               <li><a href="index">Home</a></li>
               <li><a href="login">Sign in</a></li>
               <li><a href="register">Sign up</a></li>
           </c:if>

        </ul>
    </nav>
</div>
<div class="slide-show">

    <div class="w3-content" style="max-height:600px;max-width: 100%">
        <img class="mySlides" src="https://images.unsplash.com/photo-1498761987365-a2d43c363b1e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80" style="width:100%; height: 80%;">
        <img class="mySlides" src="https://images.unsplash.com/photo-1467510107305-b118251f0aca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1953&q=80" style="width:100%; height: 80%;">
        <img class="mySlides" src="https://images.unsplash.com/reserve/2CHRCzLqSRuVRMdneTTS_unspash_003.jpg?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80" style="width:100%;height: 80%;">
    </div>

    <div class="w3-center">
        <div class="w3-section">
            <button class="w3-button w3-light-grey" onclick="plusDivs(-1)"> Prev </button>
            <button class="w3-button w3-light-grey" onclick="plusDivs(1)"> Next </button>
        </div>
    </div>

</div>
</div>
<script>
    var slideIndex = 1;
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

    function currentDiv(n) {
        showDivs(slideIndex = n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("demo");
        if (n > x.length) {slideIndex = 1}
        if (n < 1) {slideIndex = x.length}
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" w3-red", "");
        }
        x[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " w3-red";
    }

</script>

<div class="footer">
        <h5 style="text-align: center;padding: 0;margin: 0"> All rights reserved. &copy; 2019 by Hotaru_Ritsuki and AlexHack </h5>
</div>
</body>
</html>
