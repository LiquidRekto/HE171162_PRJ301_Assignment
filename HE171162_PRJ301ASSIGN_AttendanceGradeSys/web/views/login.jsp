<%-- 
    Document   : login
    Created on : Feb 19, 2023, 6:42:12 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - FPT Instructor Portal</title>
        <link rel="stylesheet" href="css/dist/index.css" />
    </head>
    <body class="">
        <h1 class="text-center text-3xl font-bold my-12">FPT Instructor Portal</h1>
        <c:choose>
            <c:when test="${requestScope.state.equals('failed')}">
                <div class="p-4 my-4 bg-red-200 w-1/4 rounded-xl my-0 mx-auto">
                    <label class="text-red-700 text-base">Incorrect email or password.</label>
                </div>
            </c:when>
            <c:when test="${requestScope.state.equals('nouser')}">
                <div class="p-4 my-4 bg-yellow-200 w-1/4 rounded-xl my-0 mx-auto">
                    <label class="text-yellow-700 text-base">Please login to continue.</label>
                </div>
            </c:when>
        </c:choose>
        
        
        <div class="p-6 bg-slate-200 my-0 mx-auto w-1/4 rounded-2xl">
            <form method="post" action="login" class="my-0 mx-auto w-5/6">
                <input type="hidden" name="targetUrl" value="${requestScope.targetUrl}">
                <label class="text-xl font-bold my-2"> Email </label> <br/>
                <input required name="email" class="my-2 w-full border-box rounded-lg border border-gray-500 bg-gray-200 p-2" type="text" placeholder="abcxyz@example.com" /> <br/>
                <label class="text-xl font-bold my-2"> Password </label> <br/>
                <input required name="password" class="my-2 w-full border-box rounded-lg border border-gray-500 bg-gray-200 p-2" type="password" placeholder="••••••••" /> <br/>
                <button type="submit" class="border-box rounded-lg bg-blue-700 w-full my-4 px-6 py-3 font-bold text-white hover:bg-blue-600">Sign in</button>
            </form>
        </div>
        </div>
    </body>
</html>
