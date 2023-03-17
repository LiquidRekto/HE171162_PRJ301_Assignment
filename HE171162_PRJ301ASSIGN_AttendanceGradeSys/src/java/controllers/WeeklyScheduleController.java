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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import utils.*;

/**
 *
 * @author Admin
 */
public class WeeklyScheduleController extends BaseRequiredAuthenticatedController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        InstructorDBContext dbctx = new InstructorDBContext(MyDBConfig.getConfig());
        ArrayList<Session> sessions = dbctx.getSessions(user.getInstructor().getInstructorId());
        
        request.setAttribute("insSessions", sessions);
        java.sql.Date[] rangeWeek = DateTimeHelper.getCurrentWeekRange();
        ArrayList<Date> dates = new ArrayList<>();
        if (request.getParameter("from") == null || request.getParameter("to") == null) {
            dates = DateTimeHelper.getListDates(rangeWeek[0], rangeWeek[1]);
        } else {
            Date from = Date.valueOf(request.getParameter("from"));
            Date to = Date.valueOf(request.getParameter("to"));
            dates = DateTimeHelper.getListDates(from, to);
        }
        TimeSlotDBContext dbts = new TimeSlotDBContext(MyDBConfig.getConfig());
        ArrayList<TimeSlot> timeSlots = dbts.all();
        
        request.setAttribute("weekRanges",DateTimeHelper.getAllWeekRangesOfYear());
        request.setAttribute("sessions", sessions);
        request.setAttribute("slots",timeSlots);
        request.setAttribute("dates",dates);
        request.getRequestDispatcher("views/schedule.jsp").forward(request, response);
    }
}
