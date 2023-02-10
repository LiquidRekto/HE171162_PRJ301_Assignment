/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import models.*;
/**
 *
 * @author Admin
 */
public class TestDBContext extends DBContext<TestMd> {

    public TestDBContext(DBConfig cfg) {
        super(cfg);
    }
    
    
    @Override
    public void insert(TestMd mdl) {
        
    }
    
    @Override
    public void update(TestMd mdl) {
        
    }

    @Override
    public void delete(TestMd mdl) {
        
    }

    @Override
    public TestMd get(int id) {
        return new TestMd();
    }

    @Override
    public ArrayList<TestMd> all() {
        ArrayList<TestMd> ls = new ArrayList<TestMd>(){};
        return ls;
    }
}
