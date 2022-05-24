<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8;>
        <title>Tour Agency</title>
    </head>

    <body bgcolor="#ff69b4">

    <h1> Choose country: </h1>

    <select value="Country" name="countrySelect" size="1">

        <c:forEach var="countryList" items="${country}">
            <option>${countryList.getCountryName()}</option>
        </c:forEach>

    </select>

    <h1> Choose city: </h1>

    <select value="City" name="citySelect" size="1">

        <c:forEach var="cityList" items="${city}">
            <option>${cityList.getCityName()}</option>
        </c:forEach>

    </select>

    <h1 style="text-align: center"> Hotels: </h1>
    <table align="center" bgcolor="#ffc0cb">

        <c:forEach items="${hotel}" var="hotelList">
            <tr>
                <td><c:out value="${hotelList.getHotelName()}"/></td>
            </tr>
        </c:forEach>

    </table>

    </body>
    </html>
</div>