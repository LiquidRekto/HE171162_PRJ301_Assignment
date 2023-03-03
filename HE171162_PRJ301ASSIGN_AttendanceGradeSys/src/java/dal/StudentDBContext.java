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
import models.Student;

/**
 *
 * @author Admin
 */
public class StudentDBContext extends DBContext<Student> {
    
    public StudentDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(Student model) {
        
    }
    
    @Override
    public void update(Student model) {
        
    }
    
    @Override
    public void delete(Student model) {
        
    }

    @Override
    public Student get(int id) {
        return new Student();
    }
    
     public ArrayList<Student> getByGroup(String groupName, String courseId) {
        ArrayList<Student> stds = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.StudentID, s.FirstName, s.MiddleName, s.LastName \n" +
"FROM Students s INNER JOIN [Join] j ON s.StudentID = j.StudentID INNER JOIN Groups g ON j.GroupID = g.GroupID\n" +
"WHERE g.GroupName = ? AND g.CourseID = ? \n" +
"ORDER BY s.StudentID";
            stm = connection.prepareStatement(sql);
            stm.setString(1, groupName);
            stm.setString(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("StudentID"));
                s.setFirstName(rs.getString("FirstName"));
                s.setMiddleName(rs.getString("MiddleName"));
                s.setLastName(rs.getString("LastName"));
                stds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stds;
    }

    public ArrayList<Student> all() {
        ArrayList<Student> stds = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT StudentID, FirstName, MiddleName, LastName FROM Students";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("StudentID"));
                s.setFirstName(rs.getString("FirstName"));
                s.setMiddleName(rs.getString("MiddleName"));
                s.setLastName(rs.getString("LastName"));
                stds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stds;
    }
}
