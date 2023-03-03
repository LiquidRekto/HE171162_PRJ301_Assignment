/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import models.Instructor;

/**
 *
 * @author Admin
 */
public class InstructorDBContext extends DBContext<Instructor> {
    
    public InstructorDBContext(DBConfig cfg) {
        super(cfg);
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
        return new ArrayList<Instructor>();
    }
}
