<%-- 
    Document   : footer
    Created on : Mar 18, 2023, 7:53:38 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="text-left bg-orange-400 z-50 p-4 fixed w-full">
            <a href="home">
            <img width="32" height="32" class="float-left bg-yellow-400 p-1 mx-4 rounded-lg hover:bg-yellow-300" src="images/home.svg" />
            </a>
            <label class="text-white font-bold text-xl">FPT Instructor Portal</label> 
            <c:if test="${not empty sessionScope.user}">
                <i onclick="toggleInfoBox()" class="px-2 rounded-lg hover:bg-yellow-200/30 select-none hover:cursor-pointer float-right text-white text-right text-xl">${sessionScope.user.getEmail()}</i> 
            </c:if>
        </div>
      <div style="display: none" id="infobox" class="bg-slate-200 p-1 z-50 right-0 top-0 overflow-hidden fixed mt-20 mr-4 rounded-lg">
          <a href="profile"><p class="hover:bg-slate-100 rounded-lg px-8 py-2 text-lg" >View Profile</p></a>
          <a href="logout"><p class="hover:bg-slate-100 rounded-lg px-8 py-2 text-lg">Log out</p></a>
      </div>
        <div class="py-10"></div><!-- comment -->
        <script>
            var inf = document.getElementById('infobox');
            function toggleInfoBox() {
                if(inf.style.display == "none") {
                    inf.style.display = "block";
                } else {
                    inf.style.display = "none";
                }
            }
        </script>
    </body>
</html>
