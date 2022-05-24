<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="navbar-nav">
                <li><a href="${pageContext.request.contextPath}/hotelInfo">Hotel</a></li>
                <c:if test="${!session.equals('true')}">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:if>
                <c:if test="${session.equals('true')}">
                    <li><a href="${pageContext.request.contextPath}/user">Cabinet</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </c:if>
            </ul>

        </div>
    </div>
</nav>
