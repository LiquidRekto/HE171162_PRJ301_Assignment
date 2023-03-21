<%-- 
    Document   : group_attendance_info
    Created on : Mar 2, 2023, 7:59:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/dist/index.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1 class="my-10 text-center text-3xl font-bold">Attendance Overview of Group: ${requestScope.group.getGroupName()} - ${requestScope.group.getCourse().getCourseCode()}</h1>
        
        <div class="relative my-0 mx-auto mb-12 w-5/6 overflow-x-auto text-center shadow-md sm:rounded-xl">
      <table class="w-full">
        <thead class="bg-blue-600 text-xs uppercase text-white">
          <tr>
            <th class="px-6 py-3" scope="col">No.</th>
            <th class="px-6 py-6" scope="col">Roll Number</th>
            <th class="px-6 py-6" scope="col">Last Name</th>
            <th class="px-6 py-6" scope="col">Middle Name</th>
            <th class="px-6 py-6" scope="col">First Name</th>
            <th class="px-6 py-6 w-1/3" scope="col">Absent Penalty</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.stsList}" var="as" varStatus="loop">
                <tr class="
                    <c:if test="${as.getAbsentSessions() ge 5}">
                        bg-yellow-100
                    </c:if>
                    ">
                    <td class="px-6 py-3" scope="col">${loop.index+1}</td>
                    <td class="px-6 py-6 text-blue-700" scope="col">${as.getStd().getStudentId()}</td>
                    <td class="px-6 py-6 text-blue-700" scope="col">${as.getStd().getLastName()}</td>
                    <td class="px-6 py-6 text-blue-700" scope="col">${as.getStd().getMiddleName()}</td>
                    <td class="px-6 py-6 text-blue-700" scope="col">${as.getStd().getFirstName()}</td>
                    <td class="px-6 py-6" scope="col">
                        <div class="relative pt-1 mx-5">
                            <div class="overflow-hidden h-2 text-xs flex rounded-xl bg-emerald-400">
                                <div style="width: ${as.getAbsentSessions()/5.0*100}%" class="shadow-none flex flex-col text-center whitespace-nowrap text-white justify-center bg-red-500"></div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
          
        </tbody>
      </table>
    </div>
     <%@include file="footer.jsp" %>
    </body>
</html>

