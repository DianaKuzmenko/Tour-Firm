<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container">

    <h3>On this page you can open a visa</h3>

    <h4>Visas you can choose</h4>
    <form method="post" action="visa">

        <select id="visaCountry" class="form-control" name="countries">

            <option value="All">All</option>
            <c:forEach var="country" items="#{countries}">

                <option value="${country.getCountryName()}">
                        ${country.getCountryName()}
                </option>

            </c:forEach>

        </select>
        <button style="margin-top: 15px;" type="submit" class="btn btn-primary">Choose country</button>

    </form>


</div>