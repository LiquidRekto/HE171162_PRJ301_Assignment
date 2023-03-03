/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import models.Group;

/**
 *
 * @author Admin
 */
public class GroupDBContext extends DBContext<Group> {
    
    public GroupDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    @Override
    public void insert(Group model) {
        
    }
    
    @Override
    public void update(Group model) {
        
    }
    
    @Override
    public void delete(Group model) {
        
    }

    @Override
    public Group get(int id) {
        return new Group();
    }

    public ArrayList<Group> all() {
        return new ArrayList<Group>();
    }
}
