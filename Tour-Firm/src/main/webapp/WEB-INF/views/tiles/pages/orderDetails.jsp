<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hotel detail information</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.12.0/moment.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>
    <script type="text/javascript">

        $(document).ready(function () {
            let beginDate = $('#beginDate');
            let endDate = $('#endDate');

            $(function () {
                beginDate.datetimepicker({
                    format: 'YYYY-MM-DD',
                });
                endDate.datetimepicker({
                    format: 'YYYY-MM-DD',
                    useCurrent: false
                });
                beginDate.on("dp.change", function (e) {
                    endDate.data("DateTimePicker").minDate(e.date);
                    console.log(beginDate.find("input").val());
                });
                endDate.on("dp.change", function (e) {
                    beginDate.data("DateTimePicker").maxDate(e.date);

                });
            });

            $('#submitBtn').click(function () {
                let begin = beginDate.find("input").val();
                let end = endDate.find("input").val()
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/orderDetails",
                    data: {beginDate: begin, endDate: end},
                    success : function(data){
                        console.log(data);
                    },
                    error : function (data) {
                        console.log(data);
                    }
                });
            })
        });




    </script>
</head>


<body>
<h1>Hotel detail information</h1>


<h3 align="center">When you want to visit this hotel?</h3>

<div class="container">
    <div class='col-md-5' title="Begin date">
        <div class="form-group">
            <div class='input-group date' id='beginDate'>
                <input type='text' class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class='col-md-5' title="End date">
        <div class="form-group">
            <div class='input-group date' id='endDate'>
                <input type='text' class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</div>

<form method="post" action="orderDetails">
    <div class="container">
        <button id="submitBtn" class="btn btn-primary" value="btn btn-primary" >Submit</button>

    </div>
</form>

</body>
</html>
