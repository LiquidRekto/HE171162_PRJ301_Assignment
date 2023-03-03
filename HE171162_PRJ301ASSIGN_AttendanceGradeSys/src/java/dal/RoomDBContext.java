/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import models.Room;

/**
 *
 * @author Admin
 */
public class RoomDBContext extends DBContext<Room> {
    
    public RoomDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(Room model) {
        
    }
    
    @Override
    public void update(Room model) {
        
    }
    
    @Override
    public void delete(Room model) {
        
    }

    @Override
    public Room get(int id) {
        
    }

    public ArrayList<Room> all() {
        
    }
}
