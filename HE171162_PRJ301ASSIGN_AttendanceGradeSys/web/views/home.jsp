<%-- 
    Document   : home
    Created on : Feb 19, 2023, 6:34:29 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - FPT Lecturer Panel</title>
        <link rel="stylesheet" href="css/dist/index.css"/>
    </head>
    <body class="flex flex-col h-screen">
        <%@include file="header.jsp" %>
        <div class="mb-auto">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <h1 class="my-12 text-center text-3xl font-bold text-gray-700"> Welcome back, ${sessionScope.user.displayName}!</h1>  
                <div class="text-center">
                    <button class="my-6 rounded-xl content-box py-6 px-12 font-bold bg-gray-300 hover:bg-orange-300 hover:text-white">Check today's attendance</button> <br/>
                    <button onclick="changeUrl('schedule')" class="my-6 rounded-xl py-6 px-12 content-box font-bold bg-gray-300 hover:bg-orange-300 hover:text-white">View Schedule</button> 
                </div>
            </c:when>
            <c:otherwise>
                <h1 class="my-12 text-center text-3xl font-bold text-gray-700">Welcome, please log in to access.</h1>  
                <div class="text-center">
                    <button onclick="changeUrl('login')" class="my-6 rounded-xl py-6 px-12 content-box font-bold bg-gray-300 hover:bg-orange-300 hover:text-white">Login</button> 
                </div>
            </c:otherwise>  
        </c:choose>
        </div>
        <%@include file="footer.jsp" %>
        <script>
            function changeUrl(target) {
                window.location.href = target;
            }
        </script>
    </body>
</html>
