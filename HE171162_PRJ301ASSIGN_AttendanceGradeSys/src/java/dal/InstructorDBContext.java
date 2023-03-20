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
public class InstructorDBContext extends DBContext<Instructor> {
    
    public InstructorDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    public Instructor getByUser(String email) {
        String sql = "SELECT i.InstructorID, i.LastName, i.MiddleName, i.FirstName FROM Instructors i\n" +
                      "WHERE i.Email = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                Instructor i = new Instructor();
                i.setInstructorId(rs.getString("InstructorID"));
                i.setLastName(rs.getString("LastName"));
                i.setMiddleName(rs.getString("MiddleName"));
                i.setFirstName(rs.getString("FirstName"));
                
                return i;
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
    
    public ArrayList<Group> getGroups(String instructorId) {
        String sql = "SELECT g.GroupID, g.GroupName, c.CourseID, c.CourseCode FROM AssignTo [at]\n" +
                     "INNER JOIN [Groups] g ON [at].GroupID = g.GroupID\n" +
                     "INNER JOIN Courses c ON c.CourseID = g.CourseID\n" +
                     "WHERE [at].InstructorID = ?";
        ArrayList<Group> groups = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            rs = stm.executeQuery();
            System.out.println("[CUSTOMINFO] Execute successful!...");
            while (rs.next()) {
                
                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("GroupName"));
                System.out.println("[CUSTOMINFO] Created group id: " + g.getGroupId());
                
                Course c = new Course();
                c.setCourseId(rs.getInt("CourseID"));
                c.setCourseCode(rs.getString("CourseCode"));
                System.out.println("[CUSTOMINFO] Created course id: " + c.getCourseId());
                g.setCourse(c);
                
                groups.add(g);
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
        return groups;
    }
    
    public ArrayList<Session> getSessions(String instructorId) {
        System.out.println("[CUSTOMINFO] Getting sessions from instructor id: " + instructorId);
        System.out.println("[CUSTOMINFO] Performing session retrieve...");
        String sql = "SELECT ses.SessionID,ses.ConductDate,ses.InstructorStatus,g.GroupID,g.GroupName,c.CourseID,c.CourseCode,r.RoomID,r.RoomName,t.SlotID,t.StartTime,t.EndTime\n" +
                     "FROM [Sessions] ses INNER JOIN [Groups] g ON ses.[Group] = g.GroupID\n" +
                     "INNER JOIN Courses c ON c.CourseID = g.CourseID\n" +
                     "INNER JOIN Rooms r ON r.RoomID = ses.Room\n" +
                     "INNER JOIN TimeSlots t ON t.SlotID = ses.TimeSlot\n" +
                     "WHERE ses.Instructor = ?";
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, instructorId);
            rs = stm.executeQuery();
            System.out.println("[CUSTOMINFO] Execute successful!...");
            while (rs.next()) {
                Session session = new Session();
                session.setConductDate(rs.getDate("ConductDate"));
                session.setInstructorStatus(rs.getBoolean("InstructorStatus"));
                session.setSessionId(rs.getInt("SessionID"));
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
        return sessions;
    }
    
    @Override
    public void insert(Instructor model) {
        
    }
    
    @Override
    public void update(Instructor model) {
        
    }
    
    @Override
    public void delete(Instructor model) {
        
    }

    @Override
    public Instructor get(int id) {
        return new Instructor();
    }

    public ArrayList<Instructor> all() {
        return new ArrayList<>();
    }
}
