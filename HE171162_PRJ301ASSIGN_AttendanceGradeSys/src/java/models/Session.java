/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Session {
    int sessionId;
    String sessionName;
    Instructor instructor;
    Group group;
    TimeSlot timeSlot;
    Room room;
    boolean instructorStatus;
    Date conductDate;

    public Session() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean getInstructorStatus() {
        return instructorStatus;
    }

    public void setInstructorStatus(boolean instructorStatus) {
        this.instructorStatus = instructorStatus;
    }

    public Date getConductDate() {
        return conductDate;
    }

    public void setConductDate(Date conductDate) {
        this.conductDate = conductDate;
    }
    
    
}
