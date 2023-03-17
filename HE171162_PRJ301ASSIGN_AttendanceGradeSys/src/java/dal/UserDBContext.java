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
 * @author sonnt
 */
public class UserDBContext extends DBContext<User> {
    
    public UserDBContext(DBConfig cfg) {
        super(cfg);
    }

    public User get(String email, String password) {
        String sql = "SELECT u.UserEmail, u.DisplayName, i.InstructorID, i.LastName, i.MiddleName, i.FirstName, i.Email\n" +
                     "FROM Instructors i\n" +
                     "INNER JOIN Users u ON i.Email = u.UserEmail\n" +
                     "WHERE u.UserEmail = ? AND u.[Password] = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next())
            {
                User s = new User();
                s.setEmail(email);
                s.setDisplayName(rs.getString("DisplayName"));
                
                Instructor i = new Instructor();
                i.setInstructorId(rs.getString("InstructorID"));
                i.setLastName(rs.getString("LastName"));
                i.setMiddleName(rs.getString("MiddleName"));
                i.setFirstName(rs.getString("FirstName"));
                
                s.setInstructor(i);
                
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
