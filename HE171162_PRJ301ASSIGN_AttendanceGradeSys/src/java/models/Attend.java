/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.Timestamp;
/**
 *
 * @author Admin
 */
public class Attend {
    int id;
    Student student;
    Session session;
    boolean status;
    String prevTaker;
    String instructorComments;
    Timestamp recordDate;

    public Attend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isPresent() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInstructorComments() {
        return instructorComments;
    }

    public void setInstructorComments(String instructorComments) {
        this.instructorComments = instructorComments;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getPrevTaker() {
        return prevTaker;
    }

    public void setPrevTaker(String prevTaker) {
        this.prevTaker = prevTaker;
    }
    
    
    
}
