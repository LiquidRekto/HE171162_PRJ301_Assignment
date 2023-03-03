/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.testing;

import cfg.MyDBConfig;
import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author Admin
 */
public class TestDBController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StudentDBContext ctx = new StudentDBContext(MyDBConfig.getConfig());
            ArrayList<Student> list = ctx.all();
            for (Student s : list) {
                resp.getWriter().print(s);
            }
            
        }
        catch (Exception e) {
            resp.getWriter().print(e);
        }
    }
    
}
