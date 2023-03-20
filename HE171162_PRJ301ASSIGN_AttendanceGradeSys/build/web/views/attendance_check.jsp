<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Attendance Check - FAP Attendace System</title>
    <link rel="stylesheet" href="css/dist/index.css" />
  </head>
  <body class="text-center">
    <%@include file="header.jsp" %>
    <h1 class="my-10 text-center text-3xl font-bold">
      Attendance Check for: <br/>
      Slot ${requestScope.chosenSes.getTimeSlot().getSlotId()} - ${requestScope.chosenSes.getGroup().getGroupName()} - ${requestScope.chosenSes.getGroup().getCourse().getCourseCode()} at ${requestScope.chosenSes.getRoom().getRoomName()}
    </h1>
    <div class="w-full py-10 text-center">
      <div class="inline-block px-2">
        <label> Taker: <b class="text-xl"> ${sessionScope.user.getInstructor().getInstructorId()} </b> </label>
      </div>
    </div>
    <form action="attendancecheck" method="POST">
    <div class="relative my-0 mx-auto mb-12 
         <c:choose>
             <c:when test="${requestScope.chosenSes.getInstructorStatus()}">
                 w-11/12 
             </c:when>
             <c:otherwise>
                 w-5/6 
             </c:otherwise>
         </c:choose>
         
         overflow-x-auto text-center shadow-md sm:rounded-xl">
        
      <table class="w-full">
          <input type="hidden" name="sessionId" value="${requestScope.chosenSes.getSessionId()}"/>
        <thead class="bg-blue-600 text-xs uppercase text-white">
          <tr>
            <th class="px-6 py-3" scope="col">No.</th>
            <th class="px-6 py-6" scope="col">Roll Number</th>
            <th class="px-6 py-6" scope="col">Last Name</th>
            <th class="px-6 py-6" scope="col">Middle Name</th>
            <th class="px-6 py-6" scope="col">First Name</th>
            <th class="px-6 py-6" scope="col">Image</th>
            <th class="px-6 py-6" scope="col">Status</th>
            <th class="px-6 py-6" scope="col">Comment</th>
            <c:if test="${requestScope.chosenSes.getInstructorStatus()}">
                <th class="px-6 py-6" scope="col">Taken By</th>
                <th class="px-6 py-6" scope="col">Recent Record</th>
             </c:if>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${requestScope.attendlist}" var="att" varStatus="loop">
              <tr>
                  <input type="hidden" name="studentId" value="${att.getStudent().getStudentId()}"/>
                  <input type="hidden" name="attend-${att.getStudent().getStudentId()}" value="${att.getId()}"/>
            <td class="px-6 py-3 text-base">${loop.index+1}</td>
            <td class="px-6 py-3 text-blue-700 text-base">${att.getStudent().getStudentId()}</td>
            <td class="px-6 py-3 text-blue-700 text-base">${att.getStudent().getLastName()}</td>
            <td class="px-6 py-3 text-blue-700 text-base">${att.getStudent().getMiddleName()}</td>
            <td class="px-6 py-3 text-blue-700 text-base">${att.getStudent().getFirstName()}</td>
            <td class="px-4 py-3 text-base object-cover text-center">
                <img width="192" class="rounded-xl inline-block" onerror="this.src='images/not-found.png'" src="../images/${att.getStudent().getStudentId()}.png">
            </td>
            <td class="px-6 py-3 text-base">
                <div class="flex items-center mb-4">
                    <input name="status-${att.getStudent().getStudentId()}" <c:if test='${!att.isPresent()}'>checked</c:if> id="absent" type="radio" value="absent" name="default-radio" class="peer/absent w-4 h-4 bg-gray-100 border-gray-300  dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"/>
    <label for="absent" class="peer-checked/absent:text-red-500 text-gray-400 ml-2 text-sm font-medium">Absent</label>
</div>
<div class="flex items-center">
    <input name="status-${att.getStudent().getStudentId()}" <c:if test='${att.isPresent()}'>checked</c:if> id="attended" type="radio" value="attended" name="default-radio" class="peer/attended w-4 h-4  bg-gray-100 border-gray-300 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:bg-gray-700 dark:border-gray-600"/>
    <label for="attended" class="peer-checked/attended:text-green-500 text-gray-400 ml-2 text-sm font-medium">Attended</label>
</div>
            </td>
            <td class="px-4 py-2 text-base">
              <textarea name="insComment-${att.getStudent().getStudentId()}" rows="6" placeholder="Write your comment here..."class="rounded-xl resize-none h-full px-2 py-1 border-gray-400 bg-slate-100 border text-left">${att.getInstructorComments()}</textarea>
            </td>
            <c:if test="${requestScope.chosenSes.getInstructorStatus()}">
                <td class="px-6 py-3 text-base">${att.getPrevTaker()}</td>
                <td class="px-6 py-3 text-base">
                    <fmt:formatDate value="${att.getRecordDate()}" pattern="dd/MM/yyyy-hh:mm:ss a" type="both"/>
                </td>
              </c:if>
              </tr>
              
          </c:forEach>
          
        </tbody>
      </table>
    </div>
    <button type="submit" class="mb-12 text-center border-box rounded-lg bg-green-500 px-6 py-3 font-bold text-white hover:bg-green-600">
        <c:choose>
            <c:when test="${requestScope.chosenSes.getInstructorStatus()}">
                Resubmit
            </c:when>
            <c:otherwise>
                Submit
            </c:otherwise>
        </c:choose>
       attendance
    </button>
    </form>
  </body>
</html>
