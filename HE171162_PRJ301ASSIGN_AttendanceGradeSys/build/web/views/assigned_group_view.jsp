<%-- 
    Document   : assigned_group_view
    Created on : Mar 19, 2023, 5:45:42 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="mb-auto">
        <h1 class="text-center font-bold my-6 text-3xl">Assigned Group(s) of: ${sessionScope.user.getInstructor().getInstructorId()}</h1>
        <div class="w-3/5 my-0 mt-4 mx-auto text-center overflow-x-auto text-center shadow-md sm:rounded-xl">
            <table class="w-full">
          <input type="hidden" name="sessionId" value="${requestScope.chosenSes.getSessionId()}"/>
        <thead class="bg-blue-600 text-xs uppercase text-white">
          <tr>
            <th class="px-6 py-3" scope="col">No.</th>
            <th class="px-6 py-3" scope="col">Group Name</th>
            <th class="px-6 py-3" scope="col">Course Code</th>
            <th class="px-6 py-3" scope="col">Course Description</th>
            <th class="px-6 py-3" scope="col"></th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.groups}" var="g" varStatus="loop">
                <tr>
                    <td class="px-6 py-3">${loop.index+1}</td>
                    <td class="px-6 py-3">${g.getGroupName()}</td>
                    <td class="px-6 py-3">${g.getCourse().getCourseCode()}</td>
                    <td class="px-6 py-3">${g.getCourse().getDescription()}</td>
                    
                    <td class="">
                        <a class="rounded-lg text-white bg-blue-600 font-bold px-3 py-2" href="groupview?groupid=${g.getGroupId()}"> View Status </a>
                    </td>
                    
                </div>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        </div>
    </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
