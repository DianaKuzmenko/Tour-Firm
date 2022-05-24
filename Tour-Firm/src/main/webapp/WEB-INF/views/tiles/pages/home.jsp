<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<style>
    <%@include file='/css/home.css' %>
</style>

<!-- Bootstrap 3 -->
<div id="carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active"
             style="background: url(<c:url value="/img/slider1.jpg"/>); background-size: 100% 100%;">
            <div class="container" style="height: 100vh; align-items: center;">
                <div class="text-center" style="margin-top: 40vh;">
                    <h4 class="text-white mb-20 text-uppercase gradient-all">Discover the Colorful World</h4>
                    <h1 class="text-uppercase text-white" style="color: #cccccc; font-size: 72px; font-weight: bold;">
                        New Trip</h1>
                </div>
            </div>
        </div>
        <div class="item"
             style="background: url(<c:url value="/img/slider2.jpg"/>); background-size: 100% 100%;">
            <div class="container" style="height: 100vh; align-items: center;">
                <div class="text-center" style="margin-top: 40vh;">
                    <h4 class="text-white mb-20 text-uppercase gradient-all">Discover the Colorful World</h4>
                    <h1 class="text-uppercase text-white" style="color: #cccccc; font-size: 72px; font-weight: bold;">
                        New Adventure</h1>
                </div>
            </div>
        </div>
        <div class="item"
             style="background: url(<c:url value="/img/slider3.jpg"/>); background-size: 100% 100%;">
            <div class="container" style="height: 100vh; align-items: center;">
                <div class="text-center" style="margin-top: 40vh;">
                    <h4 class="text-white mb-20 text-uppercase gradient-all">Discover the Colorful World</h4>
                    <h1 class="text-uppercase text-white" style="color: #cccccc; font-size: 72px; font-weight: bold;">
                        New Experience</h1>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Предыдущий</span>
    </a>
    <a class="right carousel-control" href="#carousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Следующий</span>
    </a>
</div>

<div class="section-gap info-area" id="about">
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="menu-content pb-40 col-lg-8">
                <div class="title text-center">
                    <h1 class="mb-10">Why Choose Us Your Fitness Builder</h1>
                    <p>Who are in extremely love with eco friendly system.</p>
                </div>
            </div>
        </div>
        <div class="single-info row mt-40">
            <div class="col-lg-6 col-md-12 mt-120 text-center no-padding info-left">
                <img src="<c:url value="/img/about-img.jpg"/>" class="img-fluid" alt=""/>
            </div>
            <div class="col-lg-6 col-md-12 no-padding info-rigth">
                <div class="info-content">
                    <h2 class="pb-30">We Realize that <br>
                        there are reduced <br>
                        Wastege Stand out</h2>
                    <p>
                        inappropriate behavior is often laughed off as “boys will be boys,” women face higher conduct
                        standards – especially in the workplace. That’s why it’s crucial that, as women.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="feature-area section-gap" id="secvice">
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="menu-content pb-60 col-lg-8">
                <div class="title text-center">
                    <h1 class="mb-10">Some Features that Made us Unique</h1>
                    <p>Who are in extremely love with eco friendly system.</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature mb-30">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-user"></span>
                        <h4><a href="#">Expert Technicians</a></h4>
                    </div>
                    <p>
                        Our technicians don't just take pride in their work, they are experts in their field.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature mb-30">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-license"></span>
                        <h4><a href="#">Professional Service</a></h4>
                    </div>
                    <p>
                        Our providers have specialized knowledge about niche areas of interest.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature mb-30">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-phone"></span>
                        <h4><a href="#">Great Support</a></h4>
                    </div>
                    <p>
                        Companies are finally giving customers what they really want: fast, accessible self-service.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-rocket"></span>
                        <h4><a href="#">Technical Skills</a></h4>
                    </div>
                    <p>
                        Recruiters and hiring managers are looking for candidates who can jump right in on the first day of work and start helping the company achieve its goals.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-diamond"></span>
                        <h4><a href="#">Highly Recomended</a></h4>
                    </div>
                    <p>
                        Most friendly and helpful receptionist ever, so lovely and great first impression of this Tour Agency.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 ">
                <div class="single-feature">
                    <div class="title d-flex flex-row pb-20">
                        <span class="lnr lnr-bubble"></span>
                        <h4><a href="#">Positive Reviews</a></h4>
                    </div>
                    <p>
                        A true sample of excellent hospitality!
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<div id="bottom-text"> A travel agency is a private retailer or public service that provides travel and tourism related--%>
    <%--services to the public on behalf of suppliers such as activities, airlines, car rentals, cruise lines, hotels,--%>
    <%--railways, travel insurance, and package tours. In addition to dealing with ordinary tourists, most travel agencies--%>
    <%--have a separate department devoted to making travel arrangements for business travelers; some travel agencies--%>
    <%--specialize in commercial and business travel only. There are also travel agencies that serve as general sales agents--%>
    <%--for foreign travel companies, allowing them to have offices in countries other than where their headquarters are--%>
    <%--located.--%>
<%--</div>--%>

