<%-- 
    Document   : profile
    Created on : Mar 18, 2023, 9:02:19 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile - FPT Instructor Portal</title>
        <link rel="stylesheet" href="css/dist/index.css"/>
    </head>
    <body class="flex flex-col h-screen">
        <%@include file="header.jsp" %>
        <div class="mb-auto">
            <h1 class="text-center font-bold mt-4 text-3xl">Instructor Profile </h1>
            <div class="flex w-2/5 my-0 mx-auto p-4 mt-4">
                <img class="rounded-xl w-3/7 m-8" src="hi.png" width="128" height="300" onerror="this.src='images/not-found.png'"/> 
                <div class="m-8 w-4/7 mx-auto">
                    <p class="font-bold">Instructor Full Name (L-M-F):
                        <span class="font-normal text-blue-700">
                            ${sessionScope.user.getInstructor().getLastName()} 
                            ${sessionScope.user.getInstructor().getMiddleName()} 
                            ${sessionScope.user.getInstructor().getFirstName()}
                        </span>
                    </p>
                    <p class="font-bold">Instructor Email:
                        <span class="font-normal text-blue-700">
                            ${sessionScope.user.getEmail()}
                        </span>
                    </p>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
