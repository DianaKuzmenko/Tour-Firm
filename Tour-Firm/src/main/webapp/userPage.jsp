<%--
  Created by IntelliJ IDEA.
  User: oleksii
  Date: 12.12.2019
  Time: 12:49 дп
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>

        :root {
            --input-padding-x: 1.5rem;
            --input-padding-y: 0.75rem;
        }

        .login,
        .image {
            min-height: 100vh;
        }

        .bg-image {
            background-image: url('https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80');
            background-size: cover;
            background-position: center;
        }

        .login-heading {
            font-weight: 300;
        }

        .btn-login {
            font-size: 0.9rem;
            letter-spacing: 0.05rem;
            padding: 0.75rem 1rem;
            border-radius: 2rem;
        }

        .form-label-group {
            position: relative;
            margin-bottom: 1rem;
        }

        .form-label-group > input,
        .form-label-group > label {
            padding: var(--input-padding-y) var(--input-padding-x);
            height: auto;
            border-radius: 2rem;
        }

        .form-label-group > label {
            position: absolute;
            top: 0;
            left: 0;
            display: block;
            width: 100%;
            margin-bottom: 0;
            /* Override default `<label>` margin */
            line-height: 1.5;
            color: #495057;
            cursor: text;
            /* Match the input under the label */
            border: 1px solid transparent;
            border-radius: .25rem;
            transition: all .1s ease-in-out;
        }

        .form-label-group input::-webkit-input-placeholder {
            color: transparent;
        }

        .form-label-group input:-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-moz-placeholder {
            color: transparent;
        }

        .form-label-group input::placeholder {
            color: transparent;
        }

        .form-label-group input:not(:placeholder-shown) {
            padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
            padding-bottom: calc(var(--input-padding-y) / 3);
        }

        .form-label-group input:not(:placeholder-shown) ~ label {
            padding-top: calc(var(--input-padding-y) / 3);
            padding-bottom: calc(var(--input-padding-y) / 3);
            font-size: 12px;
            color: #777;
        }

    </style>

</head>
<body>

<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4">Welcome back!</h3>
                            <form class="User_page" method="post">
                                <div class="form-label-group">
                                    <input id="Name" name="Name" class="form-control"
                                           placeholder="Name">
                                    <label for="Name">${user.firstName}</label>
                                </div>
                                <div class="form-label-group">
                                    <input id="LastName" name="LastName" class="form-control"
                                           placeholder="LastName">
                                    <label for="LastName">${user.lastName}</label>
                                </div>
                                <div class="form-label-group">
                                    <input id="Email" class="form-control"
                                           placeholder="Email" readonly>
                                    <label for="Email">${user.email}</label>
                                </div>
                                <div class="form-label-group">
                                    <input id="Country" class="form-control"
                                           placeholder="Country" readonly>
                                    <label for="Country">${user.country.name}</label>
                                </div>
                                <div class="form-label-group">
                                    <table >
                                        <thead><th>Visa</th>
                                        <th></th>
                                        <c:if test = "${visas.equals('null')}">
                                            <tr>
                                                <td>YOU DON'T HAVE VISAS,DUDE!</td>
                                            </tr>
                                            </c:if>
                                        <c:if test = "${!visas.equals('null')}">
                                        <c:forEach var="visa" items="${visas}">
                                        <tr>
                                            <td>${visa.name}</td>
                                        </tr>
                                        </c:forEach>
                                        </c:if>
                                        </thead>

                                        <tbody class="visa__table_body"></tbody>

                                    </table>
                                </div>
                                    <div class="form-label-group">
                                        Add: <select name="inputVisa">
                                        <option>Germany VISA</option>
                                        <option>Belgium VISA</option>
                                        <option>Canada VISA</option>
                                        <option>Iceland VISA</option>
                                        <option>Japan VISA</option>
                                        <option>North Korea VISA</option>
                                        <option>USA VISA</option>
                                        <option> Poland VISA</option>
                                        <option>Ukraine VISA</option>

                                            </select>
                                    </div>

                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2 finish" type="submit">
                                    Accept
                                </button>

                            </form>
                            <a href="${pageContext.request.contextPath}/index">
                            <button  class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2 finish">
                                Finish
                            </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
