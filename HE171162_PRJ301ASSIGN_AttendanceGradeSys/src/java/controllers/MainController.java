/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dal.*;
import models.*;
import cfg.*;
import controllers.authentication.BaseRequiredAuthenticatedController;

/**
 *
 * @author Admin
 */
public class MainController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
        try {
            DBContext<TestMd> ctx = new TestDBContext(MyDBConfig.getConfig());
            String fullURL = req.getRequestURL().toString();
            String ctxy = req.getContextPath();
            resp.getWriter().print("DB Connected successfully! at " + fullURL.substring(fullURL.lastIndexOf(ctxy)+ctxy.length()+1));
        }
        catch (Exception e) {
            resp.getWriter().print(e);
        }
        
    }
    
    
    
}
