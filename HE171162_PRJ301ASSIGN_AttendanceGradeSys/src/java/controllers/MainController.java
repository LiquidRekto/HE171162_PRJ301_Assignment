/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.*;
import models.*;
import cfg.*;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            DBContext<TestMd> ctx = new TestDBContext(MyDBConfig.getConfig());
            resp.getWriter().print("DB Connected successfully!");
        }
        catch (Exception e) {
            resp.getWriter().print("DB Connect failed!");
        }
        
    }
    
    
    
}
