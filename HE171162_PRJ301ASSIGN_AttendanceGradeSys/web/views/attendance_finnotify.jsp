<%-- 
    Document   : attendance_finnotify
    Created on : Mar 18, 2023, 2:12:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/dist/index.css"/>
    </head>
    <body class="flex flex-col h-screen">
        <%@include file="header.jsp" %>
        <div class="mb-auto text-center">
            <h1 class="text-center font-bold my-6 text-3xl">Submitted successfully!</h1>
            <p>Found something wrong with the latest submission? Click the button below to edit it!</p>
            <button onclick="window.location.href='attendancecheck?sesid=${requestScope.ses}'" class="my-6 mx-auto block text-center border-box rounded-lg bg-blue-500 px-6 py-3 font-bold text-white hover:bg-blue-600">
                Edit attendance
            </button>
        </div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
