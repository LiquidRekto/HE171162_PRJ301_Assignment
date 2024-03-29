
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Schedule - FAP Attendace System</title>
    <link rel="stylesheet" href="css/dist/index.css" />
  </head>
  <body>
        <%@include file="header.jsp" %>
      
    <div class="py-10"></div>
    <h1 class="my-10 text-center text-3xl font-bold">Weekly Schedule</h1>
    <div class="w-full py-10 text-center">
      <div class="inline-block px-2">
          <label class="text-xl"> Instructor: <span class="font-bold">${sessionScope.user.getInstructor().getInstructorId()}</span> </label>
    </div>
    <div class="text-center mx-0 my-8">
        <div class="inline-block pb-6 w-6 h-2 bg-green-500"></div>
        <label> Attended </label>
        <div class="inline-block pb-6 w-6 h-2 bg-cyan-500"></div>
        <label> Available </label>
        <div class="inline-block pb-6 w-6 h-2 bg-gray-500"></div>
        <label> Not available </label>
    </div>
    <div class="relative my-0 mx-auto mb-12 w-3/4 overflow-x-auto text-center shadow-md sm:rounded-xl">
      <table class="w-full">
        
        <thead class="bg-blue-600 text-xs uppercase text-white">
            <form id="weekYearForm" method="GET" action="schedule">
          <tr>
            <th class="px-6 py-2">
                  Year:
                  <select name="year" onchange="submitForm()" class="w-1/2 text-black border-box rounded-lg border border-gray-500 bg-gray-200 p-2">
                      <c:forEach items="${requestScope.yearRange}" var="y">
                          <option <c:if test="${requestScope.curYear eq y}">selected</c:if> value="${y}">
                              ${y}
                          </option>
                      </c:forEach>
                  </select>
              </th>
            <th class="px-6 pt-4" scope="col">Mon</th>
            <th class="px-6 pt-4" scope="col">Tue</th>
            <th class="px-6 pt-4" scope="col">Wed</th>
            <th class="px-6 pt-4" scope="col">Thu</th>
            <th class="px-6 pt-4" scope="col">Fri</th>
            <th class="px-6 pt-4" scope="col">Sat</th>
            <th class="px-6 pt-4" scope="col">Sun</th>
          </tr>
          <tr>
              <th class="px-6 py-2">
                  Week: 
                  <select name="week" onchange="submitForm()" class="w-1/2 text-black border-box rounded-lg border border-gray-500 bg-gray-200 p-2">
                      <c:forEach items="${requestScope.weekRanges.keySet()}" var="key">
                          <option <c:if test="${curWeek eq key}">selected</c:if> value="${key}">
                              <fmt:formatDate value="${requestScope.weekRanges.get(key)[0]}" pattern="dd/MM"/>
                               - 
                              <fmt:formatDate value="${requestScope.weekRanges.get(key)[1]}" pattern="dd/MM"/>
                          </option>
                      </c:forEach>
                  </select>
              </th>
            <c:forEach items="${requestScope.dates}" var="dl">
                <th class="px-6 pb-4 text-base" scope="col">
                    <fmt:formatDate value="${dl}" pattern="dd/MM" />
                </th>
             </c:forEach>
                <!--
            <th class="px-6 pb-4 text-base" scope="col">(6/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(7/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(8/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(9/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(10/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(11/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(12/2)</th>
                -->
          </tr>
          </form>
        </thead>
        
        <tbody>
            <c:set var="tblind" scope="session" value="0"/>
            <c:forEach items="${requestScope.slots}" var="s">
                <c:if test="${!s.getSlotId().endsWith('P')}">
                    <tr <c:if test="${tblind % 2 == 0}">class="bg-slate-100"</c:if>>
                        <td class="w-[23%]"> Slot ${s.slotId} </td>
                        <c:set var = "found" scope="session" value = "0"/>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <c:set var = "found" scope="session" value = "0"/>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${String.valueOf(ses.timeSlot.slotId.charAt(0)).equals(s.slotId) and ses.conductDate.time eq d.time}">
                                    <td class="h-3 w-[11%] text-base">
                                        <div <c:if test="${requestScope.curDate.time - d.time >= 0}"> onclick="openAttendance(${ses.getSessionId()})" </c:if> class="group group-hover:cursor-pointer h-full w-full  
                                         <c:choose>
                                             <c:when test="${requestScope.curDate.time - d.time < 0}">
                                                 bg-gray-500
                                             </c:when>
                                             <c:when test="${ses.getInstructorStatus()}">
                                                 bg-green-500
                                                 hover:bg-green-600
                                             </c:when>
                                             <c:otherwise>
                                                 bg-cyan-500
                                                 hover:bg-cyan-600
                                             </c:otherwise>
                                         </c:choose>
                                          px-2 py-3 hover:cursor-pointer">
                                        <label class="text-sm font-bold text-gray-300">${ses.getGroup().getGroupName()}</label> <br/>
                                        <label class="text-base font-bold text-white">${ses.getGroup().getCourse().getCourseCode()}</label> <br/>
                                        <label class="text-xs italic text-white">at ${ses.getRoom().getRoomName()}</label>
                                        <div class="bg-orange-400 px-2 rounded-lg">
                                            <label class="text-xs font-bold text-white">
                                                <fmt:formatDate type="time" value="${ses.getTimeSlot().getStartTime()}" pattern="H:mm" />
                                                -
                                                <fmt:formatDate type="time" value="${ses.getTimeSlot().getEndTime()}" pattern="H:mm" />
                                            </label>
                                        </div>
                                    </div>
                                    </td>
                                    <c:set var = "found" scope = "session" value = "1"/>
                                </c:if>
                            </c:forEach>
                                <c:if test="${found eq 0}">
                                    <td class="px-2 py-12 w-[11%] text-base">-</td>
                                </c:if>
                            
                        </c:forEach>
                    </tr>
                    <c:set var="tblind" scope="session" value="${tblind+1}"/>
                </c:if>      
            </c:forEach>
          
        </tbody>
      </table>
    </div>
    <%@include file="footer.jsp" %>
    <script>
        function openAttendance(location) {
            window.location.href = 'attendancecheck?sesid=' + location;
        }
        
        function setToRange(from, to) {
            window.location.href = "schedule?from=" + from + "&to=" + to;
        }
        
        var wyform = document.getElementById('weekYearForm');
        function submitForm() {
            wyform.submit();
        }

    </script>
    
  </body>
</html>
