<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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

        .form-label-group>input,
        .form-label-group>label {
            padding: var(--input-padding-y) var(--input-padding-x);
            height: auto;
            border-radius: 2rem;
        }

        .form-label-group>label {
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

        .form-label-group input:not(:placeholder-shown)~label {
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
                            <h3 class="login-heading mb-4">Nice to see you!</h3>
                            <form class="trip__create_form" method="post">
                                <div class="form-label-group">
                                    <input  id="inputName" class="form-control trip__create_startDestination" name="firstName" placeholder="Name" required>
                                    <label for="inputName">Name</label>
                                </div>

                                <div class="form-label-group">
                                    <input  id="inputLastName" class="form-control trip__create_endDestination" name="lastName" placeholder="Last Name" required>
                                    <label for="inputLastName">Last Name</label>
                                </div>
                                <div class="form-label-group">
                                    <input  id="inputEmail" type="email" class="form-control trip__create_endDestination" name="email" placeholder="Email" required>
                                    <label for="inputEmail">Email</label>
                                </div>
                                <div class="form-label-group">
                                    <input  id="inputPassword" type="password" class="form-control trip__create_endDestination" name="password" placeholder="Password" required>
                                    <label for="inputPassword">Password</label>
                                </div>
                                <div class="form-label-group">
                                    Country: <select name="country">
                                            <option>Unknown</option>
                                            <option>Ukraine</option>
                                            <option>Germany</option>
                                            <option>Poland</option>
                                        </select>
                                </div>
                                    <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2 create_trip_button" type="submit">Registration</button>
                                <div class="errorMessage">
                                    <h3>${requestScope.get("error")}</h3>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <h5 style="text-align: center;padding: 0;margin: 0"> All rights reserved. &copy; 2019 by Hotaru_Ritsuki and AlexHack </h5>
</div>

</body>
</html>