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

    @Override
    public ArrayList<Session> all() {
        return new ArrayList<>();
    }
    
    public ArrayList<Session> byInstructorInDate(String instructorId, Date date) {
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ses.SessionID,ses.SessionName, ses.ConductDate,ses.InstructorStatus,g.GroupID,g.GroupName,c.CourseID,c.CourseCode,r.RoomID,r.RoomName,t.SlotID,t.StartTime,t.EndTime\n" +
                         "FROM [Sessions] ses INNER JOIN [Groups] g ON ses.[Group] = g.GroupID\n" +
                         "INNER JOIN Courses c ON c.CourseID = g.CourseID\n" +
                         "INNER JOIN Rooms r ON r.RoomID = ses.Room\n" +
                         "INNER JOIN TimeSlots t ON t.SlotID = ses.TimeSlot\n" +
                         "WHERE ses.Instructor = ? AND ses.ConductDate = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            stm.setDate(2, date);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                session.setConductDate(rs.getDate("ConductDate"));
                session.setInstructorStatus(rs.getBoolean("InstructorStatus"));
                session.setSessionId(rs.getInt("SessionID"));
                session.setSessionName(rs.getString("SessionName"));
                System.out.println("[CUSTOMINFO] Created session id: " + session.getSessionId());
                
                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("GroupName"));
                session.setGroup(g);
                System.out.println("[CUSTOMINFO] Created group id: " + g.getGroupId());
                
                Course c = new Course();
                c.setCourseId(rs.getInt("CourseID"));
                c.setCourseCode(rs.getString("CourseCode"));
                System.out.println("[CUSTOMINFO] Created course id: " + c.getCourseId());
                g.setCourse(c);
                
                Room r = new Room();
                r.setRoomId(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                session.setRoom(r);
                
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getString("SlotID"));
                t.setStartTime(rs.getTime("StartTime"));
                t.setEndTime(rs.getTime("EndTime"));
                session.setTimeSlot(t);
                
                sessions.add(session);
                
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
