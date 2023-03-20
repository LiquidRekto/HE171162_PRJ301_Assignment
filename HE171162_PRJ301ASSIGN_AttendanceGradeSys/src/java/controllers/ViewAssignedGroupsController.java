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
import models.User;
import cfg.*;
import dal.*;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author Admin
 */
public class ViewAssignedGroupsController extends BaseRequiredAuthenticatedController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        InstructorDBContext ctx = new InstructorDBContext(MyDBConfig.getConfig());
        ArrayList<Group> groups = ctx.getGroups(user.getInstructor().getInstructorId());
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("views/assigned_group_view.jsp").forward(request,response);
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
