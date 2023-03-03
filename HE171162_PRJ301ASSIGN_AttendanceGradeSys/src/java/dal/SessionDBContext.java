/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
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
public class SessionDBContext extends DBContext<Session> {
    
    public SessionDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(Session model) {
        
    }
    
    @Override
    public void update(Session model) {
        
    }
    
    @Override
    public void delete(Session model) {
        
    }

    @Override
    public Session get(int id) {
        
    }

    public ArrayList<Session> all() {
        
    }
    
    public ArrayList<Session> byInstructorWithinDateRange(String instructorId, Date startDate, Date endDate) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT g.GroupName, g.CourseID, ts.StartTime, ts.EndTime \n" +
"FROM [Sessions] s INNER JOIN Groups g ON s.[Group] = g.GroupID INNER JOIN TimeSlots ts on s.TimeSlot = ts.SlotID\n" +
"WHERE s.Instructor = ? AND s.ConductDate BETWEEN ? AND ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            stm.setDate(2, startDate);
            stm.setDate(3, endDate);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                sessions.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sessions;
    }
}
