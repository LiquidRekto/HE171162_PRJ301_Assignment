<%-- 
    Document   : today_check
    Created on : Mar 20, 2023, 2:39:12 PM
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
            <h1 class="text-center font-bold mt-4 text-3xl">Today's available Sessions</h1>
            <div class="w-3/5 my-0 mt-4 mx-auto text-center overflow-x-auto text-center shadow-md sm:rounded-xl">
            <table class="w-full">
          <input type="hidden" name="sessionId" value="${requestScope.chosenSes.getSessionId()}"/>
        <thead class="bg-blue-600 text-xs uppercase text-white">
          <tr>
            <th class="px-6 py-3" scope="col">No.</th>
            <th class="px-6 py-3" scope="col">Course Code</th>
            <th class="px-6 py-3" scope="col">Session Name</th>
            <th class="px-6 py-3" scope="col">Time Slot</th>
            <th class="px-6 py-3" scope="col">Room</th>
            <th class="px-6 py-3" scope="col"></th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.sessions}" var="ses" varStatus="loop">
                <tr>
                    <td class="px-6 py-3">${loop.index+1}</td>
                    <td class="px-6 py-3">${ses.getGroup().getCourse().getCourseCode()}</td>
                    <td class="px-6 py-3">${ses.getSessionName()}</td>
                    <td class="px-6 py-3">${ses.getTimeSlot().getSlotId()}</td>
                    <td class="px-6 py-3">${ses.getRoom().getRoomName()}</td>
                    
                    <td class="">
                        <c:choose>
                            <c:when test="${ses.getInstructorStatus()}">
                                <a class="rounded-lg text-white bg-green-600 hover:bg-green-700 font-bold px-3 py-2" href="attendancecheck?sesid=${ses.getSessionId()}"> Edit Attendance </a>
                            </c:when>
                            <c:otherwise>
                                <a class="rounded-lg text-white bg-blue-600 hover:bg-blue-700 font-bold px-3 py-2" href="attendancecheck?sesid=${ses.getSessionId()}"> Take Attendance </a>
                            </c:otherwise>
                        </c:choose>
                        
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
