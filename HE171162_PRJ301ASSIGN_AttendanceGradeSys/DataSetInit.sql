/*CREATE DATABASE HE171162_University;
GO*/
USE HE171162_University;

CREATE TABLE Groups(
	[GroupID] VARCHAR(8) PRIMARY KEY,
	GroupName VARCHAR(16)
);
CREATE TABLE Rooms(
	RoomID VARCHAR(8) PRIMARY KEY,
	RoomNumber VARCHAR(8),
	Building VARCHAR(64)
);

CREATE TABLE Courses(
	CourseID VARCHAR(8) PRIMARY KEY,
	Code VARCHAR(16),
	CourseDescription NVARCHAR(1024)
);

CREATE TABLE Instructors(
	InstructorID VARCHAR(8) PRIMARY KEY,
	FamilyName NVARCHAR(256),
	[Name] NVARCHAR(256)
);
/*
CREATE TABLE [Status](
);

CREATE TABLE RecordTime(
);
*/

CREATE TABLE TimeSlots (
	SlotID VARCHAR(8) PRIMARY KEY,
	SlotCode VARCHAR(3),
	[Description] VARCHAR(255)
);


/* Main shit */
CREATE TABLE Students(
	RollNum VARCHAR(8) PRIMARY KEY,
	FamilyName NVARCHAR(256),
	[Name] NVARCHAR(256),
	[Group] VARCHAR(8),
	FOREIGN KEY ([Group]) REFERENCES Groups(GroupID)

);

/* Also main shit */
CREATE TABLE [Sessions] (
	SessionID VARCHAR(8) PRIMARY KEY,
	Instructor VARCHAR(8),
	[Group] VARCHAR(8),
	TimeSlot VARCHAR(8),
	Room VARCHAR(8),
	FOREIGN KEY (Instructor) REFERENCES Instructors(InstructorID),
	FOREIGN KEY ([Group]) REFERENCES Groups(GroupID),
	FOREIGN KEY (TimeSlot) REFERENCES TimeSlots(SlotID),
	FOREIGN KEY (Room) REFERENCES Rooms(RoomID)
);





