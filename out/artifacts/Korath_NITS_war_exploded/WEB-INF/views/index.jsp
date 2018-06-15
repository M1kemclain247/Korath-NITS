<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>NITS</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="narrow-jumbotron.css" rel="stylesheet">
</head>
<body>


<!-- Bootstrap core CSS -->
<head>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
            padding: 5px 5px;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #4CAF50;
        }


    </style>
</head>
<ul>
    <li><a href="/?category=1">NITS</a></li>
    <li><a href="/?category=3">Cloud Security</a></li>
    <li><a href="/?category=2">Amadeus</a></li>
</ul>

<br>
<br>

<br>
<br>

<div class="container">

<%int i = 0; %>

<c:if test="${not empty hosts}">
    <c:forEach items="${hosts}" var="host">
        <% i++; %>

        <% if (i == 1) { %>
        <div class="row">
            <%}%>

            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="wrap-item text-center">
                    <div class="item-img">
                        <a href="${host.ip}"><img src="${host.img_path}" width="200px" height="200px"></a>
                    </div>
                    <h3 class="pad-bt15">${host.name}</h3>
                </div>
            </div>

            <% if (i == 4) { %>
        </div>
        <% i = 0; %>
        <%}%>

    </c:forEach>
</c:if>

</div>

<br>
<br>
<br>
<br>

<footer id="footer">
    <div class="container">
        <div class="row text-center">
            <p>&copy; National IT Services. All Rights Reserved.</p>
        </div>
    </div>
</footer>


<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/jquery.easing.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/wow.js"></script>
<script src="/resources/js/jquery.bxslider.min.js"></script>
<script src="/resources/js/custom.js"></script>

</body>
</html>