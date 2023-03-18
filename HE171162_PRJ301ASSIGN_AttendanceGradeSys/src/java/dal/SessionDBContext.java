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
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ses.SessionID, c.CourseCode, ses.SessionName, i.LastName, i.MiddleName, i.FirstName, g.GroupName,\n" +
                         "ses.TimeSlot, t.StartTime, t.EndTime, r.RoomName, ses.InstructorStatus\n" +
                         "FROM [Sessions] ses\n" +
                         "INNER JOIN [Groups] g ON ses.[Group] = g.GroupID\n" +
                         "INNER JOIN [Instructors] i ON ses.Instructor = i.InstructorID\n" +
                         "INNER JOIN [TimeSlots] t ON ses.[TimeSlot] = t.SlotID\n" +
                         "INNER JOIN [Courses] c ON g.CourseID = c.CourseID\n" +
                         "INNER JOIN [Rooms] r ON ses.[Room] = r.RoomID\n" +
                         "WHERE ses.SessionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Session s = new Session();
                s.setSessionId(id);
                s.setSessionName(rs.getString("SessionName"));
                s.setInstructorStatus(rs.getBoolean("InstructorStatus"));
                
                Instructor i = new Instructor();
                i.setFirstName(rs.getString("FirstName"));
                i.setMiddleName(rs.getString("MiddleName"));
                i.setLastName(rs.getString("LastName"));
                s.setInstructor(i);
                
                Group g = new Group();
                g.setGroupName(rs.getString("GroupName"));
                
                
                Course c = new Course();
                c.setCourseCode(rs.getString("CourseCode"));
                g.setCourse(c);
                s.setGroup(g);
                
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getString("TimeSlot"));
                t.setStartTime(rs.getTime("StartTime"));
                t.setEndTime(rs.getTime("EndTime"));
                s.setTimeSlot(t);
                
                Room r = new Room();
                r.setRoomName(rs.getString("RoomName"));
                s.setRoom(r);
                
                return s;
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
        return null;
   
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
