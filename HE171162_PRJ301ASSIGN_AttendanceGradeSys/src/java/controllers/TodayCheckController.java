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
import java.util.ArrayList;
import utils.*;

/**
 *
 * @author Admin
 */
public class TodayCheckController extends BaseRequiredAuthenticatedController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        SessionDBContext ctx = new SessionDBContext(MyDBConfig.getConfig());
        ArrayList<Session> sessions = ctx.byInstructorInDate(user.getInstructor().getInstructorId(), DateTimeHelper.getTodayDate());
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("views/today_check.jsp").forward(request,response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response, user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response, user);
    }
    
}
