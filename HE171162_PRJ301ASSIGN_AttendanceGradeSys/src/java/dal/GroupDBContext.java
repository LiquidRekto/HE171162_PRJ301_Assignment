/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;

/**
 *
 * @author Admin
 */
public class GroupDBContext extends DBContext<Group> {
    
    public GroupDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(Group model) {
        
    }
    
    @Override
    public void update(Group model) {
        
    }
    
    @Override
    public void delete(Group model) {
        
    }

    @Override
    public Group get(int id) {
         String sql = "SELECT g.GroupName, c.CourseCode FROM Groups g \n" +
                      "INNER JOIN Courses c ON g.CourseID = c.CourseID \n" +
                      "WHERE GroupID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Group g = new Group();
                g.setGroupName(rs.getString("GroupName"));
                
                Course c = new Course();
                c.setCourseCode(rs.getString("CourseCode"));
                g.setCourse(c);
                
                return g;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public String getGroupName(int groupId) {
        String sql = "SELECT GroupName FROM Groups WHERE GroupID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("GroupName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Group> all() {
        return new ArrayList<Group>();
    }
}
