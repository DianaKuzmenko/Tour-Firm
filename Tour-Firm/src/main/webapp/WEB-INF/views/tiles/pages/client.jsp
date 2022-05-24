<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <h2>Welcome to Private Cabinet</h2>

    <div class="infoSignInUser">You login as <c:out value="${client.getClientName()}"/> <c:out
            value="${client.getClientSurname()}"/></div>

    <table style="margin-top: 30px" class="table">

        <thead>
        <tr>
            <th>Available countries:</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="country" items="${visaCountryList}">
            <tr>
                <td>
                    <option value="${country.getCountryName()}">
                            ${country.getCountryName()}
                    </option>
                </td>
                <td><a href="/deleteVisa?idClient=<c:out value='${country.getIdCountry()}'/>"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <a href="${base}/visa" class="btn btn-primary">Add new Visa</a>

    <%--<table class="table table-striped">--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th>Name</th>--%>
    <%--<th>Surname</th>--%>
    <%--<th>Phone number</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>

    <%--<tbody>--%>
    <%--<tr>--%>
    <%--<td><c:out value="${client.getClientName()}" /></td>--%>
    <%--<td><c:out value="${client.getClientSurname()}" /></td>--%>
    <%--<td><c:out value="${client.getPhoneNumber()}" /></td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
    <%--</table>--%>
    <table>

    <table class="table">
        <thead>
        <tr>
            <th>Orders from user</th>
        </tr>
        </thead>
        <thead>
        <tr>
            <%--<th>Id Order</th>--%>
            <%--<th>Client</th>--%>
            <th>Hotel</th>
            <th>Begin Date</th>
            <th>End Date</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="ordersList" items="${orderdetails}">
            <tr>
                    <%--<td><c:out value="${ordersList.getId()}" /></td>--%>
                <%--<td><c:out value="${ordersList.getClient().getClientName()}" /></td>--%>
                <td><c:out value="${ordersList.getHotel().getHotelName()}" /></td>
                <td><c:out value="${ordersList.getBeginDate()}" /></td>
                <td><c:out value="${ordersList.getEndDate()}" /></td>
                    <%--<td><a href="/hall?id=<c:out value='${orderDetailsList.getId()}'/>&action=edit"><span class="glyphicon glyphicon-pencil"></span></a></td>--%>
                <td><a href="${pageContext.request.contextPath}/orderDelete?idOrder=<c:out value='${ordersList.getId()}'/>"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

