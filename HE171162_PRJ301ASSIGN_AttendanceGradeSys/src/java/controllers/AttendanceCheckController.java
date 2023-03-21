/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.authentication.BaseRequiredAuthenticatedController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.*;
import dal.*;
import cfg.*;
import java.sql.*;
import utils.*;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Admin
 */
public class AttendanceCheckController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String[] sids = request.getParameterValues("studentId");
        String taker = user.getInstructor().getInstructorId();
        int sesid = Integer.parseInt(request.getParameter("sessionId"));
        ArrayList<Attend> attendances = new ArrayList<>();
        for (String sid : sids) {
            Student s = new Student();
            s.setStudentId(sid);
            Attend a = new Attend();
            a.setId(Integer.parseInt(request.getParameter("attend-"+sid)));
            a.setStudent(s);
            a.setStatus(request.getParameter("status-"+sid).equals("attended"));
            a.setInstructorComments(request.getParameter("insComment-"+sid));
            attendances.add(a);
        }
        AttendDBContext db = new AttendDBContext(MyDBConfig.getConfig());
        db.updateAttendance(attendances, sesid, taker);
        request.setAttribute("ses",request.getParameter("sessionId"));
        request.getRequestDispatcher("views/attendance_finnotify.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String sessParam = request.getParameter("sesid");
        
        if (sessParam == null) {
            response.sendRedirect("home");
        } else {
            int sesId = Integer.parseInt(sessParam);
            AttendDBContext ctx = new AttendDBContext(MyDBConfig.getConfig());
            ArrayList<Attend> attendances = ctx.getAttendanceBySessionId(sesId);
            for (Attend a : attendances) {
                System.out.println(a.getStudent().getStudentId());
            }
            Calendar cl = Calendar.getInstance();
            
            SessionDBContext sesctx = new SessionDBContext(MyDBConfig.getConfig());
            Session ses = sesctx.get(sesId);
            cl.setTime(DateTimeHelper.convertSqlToUtilDate(ses.getConductDate()));
            Time time = ses.getTimeSlot().getStartTime();
            int hour = time.getHours();
            int minute = time.getMinutes();
            int second = time.getSeconds();
            System.out.println(time + " " + hour + " " + minute + " " + second);
            cl.set(Calendar.HOUR, hour);
            cl.set(Calendar.MINUTE, minute);
            cl.set(Calendar.SECOND, second);
            cl.add(Calendar.DATE, 1);
            Timestamp due = DateTimeHelper.convertUtilToSqlTimestamp(cl.getTime());
            request.setAttribute("attendlist", attendances);
            request.setAttribute("chosenSes", ses);
            request.setAttribute("dueDate",due);
            request.setAttribute("isOverdue", DateTimeHelper.getTodayDateWithTime().getTime() - due.getTime() > 0);
            request.getRequestDispatcher("views/attendance_check.jsp").forward(request, response);
        }
        
    }
    
}
