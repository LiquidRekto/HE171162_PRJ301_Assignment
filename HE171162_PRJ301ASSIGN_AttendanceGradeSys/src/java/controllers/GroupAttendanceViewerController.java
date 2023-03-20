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
import cfg.*;
import dal.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class GroupAttendanceViewerController extends BaseRequiredAuthenticatedController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String gid_raw = request.getParameter("groupid");
        try {
            if (gid_raw != null) {
                int gid = Integer.parseInt(gid_raw);
                AttendDBContext ctx = new AttendDBContext(MyDBConfig.getConfig());
                ArrayList<AttendStatus> status = ctx.getGroupAttendanceStatus(gid);
                for (AttendStatus x : status) {
                    System.out.println(x.getAbsentSessions());
                }
                GroupDBContext gctx = new GroupDBContext(MyDBConfig.getConfig());
                request.setAttribute("group", gctx.getGroupName(gid));
                request.setAttribute("stsList", status);
                request.getRequestDispatcher("views/group_attendance_info.jsp").forward(request, response);
                
            } else {
                response.getWriter().println("Not available");
            }
        }
        catch (Exception e) {
            response.getWriter().println("Invalid input");
        }
        
        
    }
}
