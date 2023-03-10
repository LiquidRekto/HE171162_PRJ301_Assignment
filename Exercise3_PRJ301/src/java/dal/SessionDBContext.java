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
import model.Session;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {
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
        return new Session();
    }
    
    public Session getByDateAndSlot(Date date, int slotid) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Session s = new Session();
        try {
            String sql = "SELECT g.gname, s.[description] as subjdesc, l.lname, r.rname, t.[description] as timedesc\n" +
"FROM [Session] sess INNER JOIN Room r ON sess.rid = r.rid INNER JOIN [Subject] s ON sess.[sid] = s.[sid]\n" +
"INNER JOIN TimeSlot t ON sess.tid = t.tid INNER JOIN Lecturer l ON sess.lid = l.lid\n" +
"INNER JOIN [Group] g ON sess.gid = g.gid\n" +
"WHERE [date] = ? AND t.tid = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, slotid);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                
                s.setGroupName(rs.getString("gname"));
                s.setSubject(rs.getString("subjdesc"));
                s.setLecturer(rs.getString("lname"));
                s.setRoom(rs.getString("rname"));
                s.setTimeSlot(rs.getString("timedesc"));
                
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
        return s;
    }

    @Override
    public ArrayList<Session> all() {
        return new ArrayList<Session>();
    }
}
