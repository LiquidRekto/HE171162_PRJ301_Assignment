/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bùi Tiến Dũng - aka LiquidRekto (HE171162)
 */
public abstract class DBContext<T> {
    protected Connection connection;

    public DBContext(DBConfig cfg) {
        try {
            String url =  String.format("jdbc:sqlserver://localhost\\%s:%d;databaseName=%s",cfg.getServerName(), cfg.getPort(), cfg.getTargetDatabase());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, cfg.getUser(), cfg.getPass());
        }
        catch (ClassNotFoundException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }  
    }
    
    public abstract void insert(T model);

    public abstract void update(T model);

    public abstract void delete(T model);

    public abstract T get(int id);

    public abstract ArrayList<T> all();
    
    
}
