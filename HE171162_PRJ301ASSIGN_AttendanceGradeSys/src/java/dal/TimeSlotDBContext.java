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
import models.TimeSlot;

/**
 *
 * @author Admin
 */
public class TimeSlotDBContext extends DBContext<TimeSlot> {
    
    public TimeSlotDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(TimeSlot model) {
        
    }
    
    @Override
    public void update(TimeSlot model) {
        
    }
    
    @Override
    public void delete(TimeSlot model) {
        
    }

    @Override
    public TimeSlot get(int id) {
        return new TimeSlot();
    }

    public ArrayList<TimeSlot> all() {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.SlotID, t.StartTime, t.EndTime FROM TimeSlots t";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getString("SlotID"));
                t.setStartTime(rs.getTime("StartTime"));
                t.setEndTime(rs.getTime("EndTIme"));
                slots.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return slots;
    }
}
