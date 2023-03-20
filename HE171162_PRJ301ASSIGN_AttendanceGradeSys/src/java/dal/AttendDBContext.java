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
import utils.*;

/**
 *
 * @author Admin
 */
public class AttendDBContext extends DBContext<Attend> {

    public AttendDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    public void updateAttendance(ArrayList<Attend> attendList ,int sesId, String taker) {
        ArrayList<PreparedStatement> stms = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            //UPDATE Session Record
            String sql_update_session = "UPDATE [Sessions] SET [InstructorStatus] = 1 WHERE SessionID = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sesId);
            stm_update_session.executeUpdate();
            stms.add(stm_update_session);
            java.sql.Timestamp submitDateTime = DateTimeHelper.convertUtilToSqlTimestamp(new java.util.Date());
            //PROCESS Attendace records
            for (Attend att : attendList) {
                if (att.getId() == -1) //INSERT
                {
                    String sql_insert_att = "INSERT INTO \n" +
                                            "Attend \n" +
                                            "(Student, [Session], [Status], RecordDate, TakenBy, InstructorComment) \n" +
                                            "VALUES\n" +
                                            "(?,?,?,?,?,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setString(1, att.getStudent().getStudentId());
                    stm_insert_att.setInt(2, sesId);
                    stm_insert_att.setBoolean(3, att.isPresent());
                    stm_insert_att.setTimestamp(4, submitDateTime);
                    stm_insert_att.setString(5, taker);
                    stm_insert_att.setString(6, att.getInstructorComments());
                    stm_insert_att.executeUpdate();
                    stms.add(stm_insert_att);
                    
                } else //UPDATE
                {
                    String sql_update_att = "UPDATE Attend SET [Status] = ?, InstructorComment = ?, RecordDate = ? WHERE AttendID = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, att.isPresent());
                    stm_update_att.setString(2, att.getInstructorComments());
                    stm_update_att.setTimestamp(3, submitDateTime);
                    stm_update_att.setInt(4, att.getId());
                    stm_update_att.executeUpdate();
                    stms.add(stm_update_att);
                }
            }

            connection.commit();
        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PreparedStatement stm : stms) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<AttendStatus> getGroupAttendanceStatus(int groupId) {
        ArrayList<AttendStatus> attStats = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.StudentID, s.LastName, s.MiddleName, s.FirstName, SUM(CASE WHEN a.[Status] = 0 THEN 1 ELSE 0 END) AS NumAbsentSessions FROM\n" +
                         "Students s INNER JOIN [Join] j ON j.StudentID = s.StudentID\n" +
                         "INNER JOIN [Groups] g ON g.GroupID = j.GroupID\n" +
                         "INNER JOIN [Sessions] ses ON ses.[Group] = g.GroupID\n" +
                         "LEFT JOIN [Attend] a ON a.Student = s.StudentID AND a.[Session] = ses.SessionID\n" +
                         "WHERE g.GroupID = ?\n" +
                         "GROUP BY s.StudentID, s.LastName, s.MiddleName, s.FirstName\n" +
                         "ORDER BY s.StudentID";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                AttendStatus as = new AttendStatus();
                
                Student s = new Student();
                s.setStudentId(rs.getString("StudentID"));
                s.setLastName(rs.getString("LastName"));
                s.setMiddleName(rs.getString("MiddleName"));
                s.setFirstName(rs.getString("FirstName"));
                
                as.setStd(s);
                as.setAbsentSessions(rs.getInt("NumAbsentSessions"));
                
                attStats.add(as);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attStats;
    }
    
    public ArrayList<Attend> getAttendanceBySessionId(int sesId) {
        ArrayList<Attend> attendances = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT\n" +
                         "s.StudentID,s.LastName,s.MiddleName,s.FirstName,\n" +
                         "ISNULL(a.AttendID,-1) as AttendID,ISNULL(a.[Status],0) as [Status],ISNULL(a.TakenBy,'') as [TakenBy], a.RecordDate , ISNULL(a.[InstructorComment],'') as [Comments]\n" +
                         "FROM Students s INNER JOIN [Join] j ON j.StudentID = s.StudentID\n" +
                         "INNER JOIN [Groups] g ON g.GroupID = j.GroupID\n" +
                         "INNER JOIN [Sessions] ses ON ses.[Group] = g.GroupID\n" +
                         "LEFT JOIN [Attend] a\n" +
                         "ON a.Student = s.StudentID AND a.[Session] = ses.SessionID\n" +
                         "WHERE ses.SessionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sesId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attend a = new Attend();
                a.setId(rs.getInt("AttendID"));
                a.setStatus(rs.getBoolean("Status"));
                a.setPrevTaker(rs.getString("TakenBy"));
                a.setRecordDate(rs.getTimestamp("RecordDate"));
                a.setInstructorComments(rs.getString("Comments"));
                
                Student s = new Student();
                s.setStudentId(rs.getString("StudentID"));
                s.setLastName(rs.getString("LastName"));
                s.setMiddleName(rs.getString("MiddleName"));
                s.setFirstName(rs.getString("FirstName"));
                a.setStudent(s);
                
                attendances.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attendances;
    }

    @Override
    public void insert(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attend get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attend> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
