/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class AttendStatus {
    Student std;
    int absentSessions;

    public AttendStatus() {
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public int getAbsentSessions() {
        return absentSessions;
    }

    public void setAbsentSessions(int absentSessions) {
        this.absentSessions = absentSessions;
    }
    
    
}
