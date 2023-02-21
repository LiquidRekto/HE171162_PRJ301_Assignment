/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cfg;
import dal.DBConfig;
/**
 *
 * @author Admin
 */
public class MyDBConfig {
    public static DBConfig getConfig() {
        // Config stuffs
        
        DBConfig cfg = new DBConfig();
        cfg.setUser("liquidrekto");
        cfg.setPass("Dung6c@@");
        cfg.setServerName("MSSQLSERVER");
        cfg.setPort(1433);
        cfg.setTargetDatabase("HE171162_University");
        
        return cfg;
    }
}
