<%-- 
    Document   : home
    Created on : Feb 19, 2023, 6:34:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - FPT Lecturer Panel</title>
        <link rel="stylesheet" href="css/dist/index.css"/>
    </head>
    <body>
        <body>
        <div class="bg-orange-400 p-4 fixed w-full">
            <p class="text-white font-bold text-xl">FPT Academic Portal</p> 
        </div>
        <div class="py-10"></div><!-- comment -->
        <h1 class="my-12 text-center text-3xl font-bold text-gray-700"> Welcome back, ${sessionScope.user.displayName}!</h1>  
        <div class="text-center">
            <button class="my-6 rounded-xl content-box py-6 px-12 font-bold bg-gray-300 hover:bg-orange-300 hover:text-white">Check today's attendance</button> <br/>
            <button onclick="changeUrl('schedule')" class="my-6 rounded-xl py-6 px-12 content-box font-bold bg-gray-300 hover:bg-orange-300 hover:text-white">View Schedule</button> 
        </div>
        <script>
            function changeUrl(target) {
                window.location.href = target;
            }
        </script>
    </body>
</html>
