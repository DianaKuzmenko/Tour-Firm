
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">

        $country = $("#countrySelect");
        $(document).ready(function () {
            $(document).on("#countrySelect", "change", (function () {
                $("select option:selected").first().each(function () {

                    let country = $(this).text();
                    $.ajax({
                        url: "/hotelInfo",
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify({country: [JSON.stringify(country)], city: ["All"]}),
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        success: function (data) {
                            $country
                                .find('option')
                                .remove()
                                .end();
                            $country.append($("<option>").attr(data))
                        },
                        error: function (data, status, er) {
                            alert("error: " + data + " status: " + status + " er:" + er);
                        }
                    });
                });
            }));
        });

        $(document).on("#countrySelect", "change", (function () {
                let countryName = $(this).text();
                getCities(countryName);

                $.ajax({
                    type: "POST",
                    url: "/hotelInfo",
                    data: {country: countryName, city: "All"},
                    success: function (data) {
                        console.log("ajax success: on select country");
                        alert("data: " + data + "status: " + status);
                        $country.text(data)
                    },
                    error: function () {
                        console.log("ajax error: on select country");
                        alert("error: " + data + " status: " + status + " er:" + er);
                    }
                });
            })
        );

        function getCountry(obj) {
            let countryName = obj.options[obj.selectedIndex].value;
            $.ajax({
                url: "/hotelInfo",
                dataType: "json",
                data: {country: countryName, city: "All", hotel: Object},
                type: "POST",
                success: function (data) {
                    console.log(data.city);
                    let items = "";
                    items += '<option value="All">All</option>';
                    for (let i = 0; i < data.city.length; i++) {
                        items += '<option value="' + data.city[i] + '">' + data.city[i] + '</option>';
                    }
                    $("#citySelect").html(items);

                    let hotels = "";
                    for (let i = 0; i < data.hotel.length; i++) {
                        hotels += '<tr><td>' + data.hotel[i]['hotelName'] + '</td>' +
                            '<td>' + data.hotel[i]['city']['cityName'] + '</td>' +
                            '<td>' + data.hotel[i]['city']['country']['countryName'] + '</td>' +
                            '<td>' + data.hotel[i]['availableCount'] + '</td>' +
                            '<td id="bookBtn"><a href="/orderDetails" class="btn btn-primary">Book</a></td></tr>';
                    }
                    $("#hotelBody").html(hotels);

                },
                error: function () {
                    alert("An error occurred in getCountry(): ");
                }
            });
        }

        function getCities(obj) {
            let city = obj.options[obj.selectedIndex].value;
            let country = document.getElementById("countrySelect").value;
            $.ajax({
                url: "/hotelInfo",
                data: {country: country, city: city, hotel: Object},
                type: "POST",
                success: function (data) {
                    console.log(data.hotel[0]);
                    let items = "";
                    for (let i = 0; i < data.hotel.length; i++) {
                        items += '<tr><td>' + data.hotel[i]['hotelName'] + '</td>' +
                            '<td>' + data.hotel[i]['city']['cityName'] + '</td>' +
                            '<td>' + data.hotel[i]['city']['country']['countryName'] + '</td>' +
                            '<td>' + data.hotel[i]['availableCount'] + '</td>' +
                            '<td id="bookBtn"><a href="/orderDetails" class="btn btn-primary">Book</a></td></tr>';

                    }
                    $("#hotelBody").html(items);
                },
                error: function () {
                }
            });
        }

        function openOrderDetails(obj) {
            let hotelName = $('#bookBtn').attr("title");
            // let name = obj.option[obj.title].value;
            let name1 = obj.title;

            document.getElementById("hiddenHotelData").value = name1;

            console.log(hotelName);

        }
    </script>
</head>

<div class="row" id="selectors">
    <div class="col-md-2 col-xs-12">
        <form action="hotelInfo" method="post">
            <div class="form-group">
                <label class="control-lavel col-sm-12">Country</label>

                <select id="countrySelect" class="form-control" name="country" title="Country"
                        onchange="getCountry(this)">
                    <option value="All">All</option>
                    <c:forEach var="countryList" items="${country}">
                        <option id="countryOption" value="${countryList.getCountryName()}">
                                ${countryList.getCountryName()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label class="contol-label col-sm-12">City</label>
                <select id="citySelect" class="form-control" name="city" title="City" style="visibility: visible"
                        onchange="getCities(this)">
                    <option value="All">All</option>
                    <c:forEach var="cityList" items="${city}">
                        <option id="cityOptions" value="${cityList.getCityName()}">
                                ${cityList.getCityName()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <a href="/hotelInfo" class="btn btn-primary">Reset</a>
        </form>
    </div>
</div>

<form action="orderDetails" method="post">
    <div class="container">
        <h1 style="color: #0A0B0D">List of Hotels: </h1>
        <table class="table" id="hotelTable">
            <thead>
            <tr>
                <th>Hotel</th>
                <th>City</th>
                <th>Country</th>
                <th>Available Count</th>
            </tr>
            </thead>

            <tbody id="hotelBody">
            <c:forEach var="hotelList" items="${hotel}">
                <tr>
                    <td id="hotelName"><c:out value="${hotelList.getHotelName()}"/></td>
                    <td><c:out value="${hotelList.getCity().getCityName()}"/></td>
                    <td><c:out value="${hotelList.getCity().getCountry().getCountryName()}"/></td>
                    <td><c:out value="${hotelList.getAvailableCount()}"/></td>
                    <%--<td><a href="/orderDetails" class="btn btn-primary">Book</a></td>--%>
                    <td title="${hotelList.getHotelName()}" onclick="openOrderDetails(this)">
                        <input type="hidden" id="hiddenHotelData" name="data-hotelName" value="">
                        <button type="submit" class="btn btn-primary bookbtn" value="btn btn-primary">Book</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

</form>


