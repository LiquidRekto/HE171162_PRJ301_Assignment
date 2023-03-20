/* 
	Force delete the database every time this SQL file is executed. (if it exists)
*/
USE master;
IF db_id('HE171162_University') IS NOT NULL
ALTER DATABASE [HE171162_University] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE [HE171162_University];
go

/* 
	Create database
*/
CREATE DATABASE [HE171162_University];
go
use [HE171162_University];

/* 
	Create tables
*/


CREATE TABLE Rooms(
	RoomID INT PRIMARY KEY NOT NULL IDENTITY(1, 1),
	RoomName VARCHAR(8) UNIQUE
);

CREATE TABLE Courses(
	CourseID INT PRIMARY KEY NOT NULL IDENTITY(1, 1),
	CourseCode VARCHAR(16) UNIQUE,
	CourseDescription NVARCHAR(1024)
);

CREATE TABLE [Users] (
	UserEmail VARCHAR(256) PRIMARY KEY,
	[Password] VARCHAR(256),
	DisplayName VARCHAR(256),
	CONSTRAINT ck_UserEmail CHECK (UserEmail LIKE '%_@__%.__%')
);

CREATE TABLE Instructors(
	InstructorID VARCHAR(32) PRIMARY KEY,
	FirstName NVARCHAR(256),
	MiddleName NVARCHAR(256),
	LastName NVARCHAR(256),
	Email VARCHAR(256),
	FOREIGN KEY (Email) REFERENCES [Users](UserEmail)
);



CREATE TABLE Groups (
	GroupID INT PRIMARY KEY NOT NULL IDENTITY(1, 1),
	GroupName VARCHAR(16),
	CourseID INT,
	FOREIGN KEY (CourseID) REFERENCES Courses(CourseID),
	CONSTRAINT uk_Group_Course UNIQUE (GroupName, [CourseID])

);

CREATE TABLE TimeSlots (
	SlotID VARCHAR(2) PRIMARY KEY,
	StartTime TIME,
	EndTime TIME
);


/* Kinda main table */
CREATE TABLE Students(
	StudentID VARCHAR(8) PRIMARY KEY,
	FirstName NVARCHAR(256),
	MiddleName NVARCHAR(256),
	LastName NVARCHAR(256)
);

/* Main table*/
CREATE TABLE [Sessions] (
	SessionID INT PRIMARY KEY NOT NULL IDENTITY(1, 1),
	SessionName TEXT,
	Instructor VARCHAR(32),
	[Group] INT,
	TimeSlot VARCHAR(2),
	Room INT,
	InstructorStatus BIT,
	ConductDate DATE,
	FOREIGN KEY (Instructor) REFERENCES Instructors(InstructorID),
	FOREIGN KEY ([Group]) REFERENCES Groups(GroupID),
	FOREIGN KEY (TimeSlot) REFERENCES TimeSlots(SlotID),
	FOREIGN KEY (Room) REFERENCES Rooms(RoomID),
	CONSTRAINT uk_SessionEntity UNIQUE ([Group], TimeSlot, ConductDate)
);

CREATE TABLE [Join](
	StudentID VARCHAR(8),
	GroupID INT,
	FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY (GroupID) REFERENCES Groups(GroupID),
	CONSTRAINT uk_StudentGroupPair UNIQUE (StudentID, GroupID)
);

CREATE TABLE AssignTo(
	InstructorID VARCHAR(32),
	GroupID INT,
	FOREIGN KEY (InstructorID) REFERENCES Instructors(InstructorID),
	FOREIGN KEY (GroupID) REFERENCES Groups(GroupID),
	CONSTRAINT uk_InstructorGroupPair UNIQUE (InstructorID, GroupID)
);


/* N - N TABLES */
CREATE TABLE Attend (
	AttendID INT PRIMARY KEY NOT NULL IDENTITY(1, 1),
	Student VARCHAR(8) NOT NULL,
	[Session] INT NOT NULL,
	[Status] BIT,
	RecordDate DATETIME,
	TakenBy VARCHAR(32),
	InstructorComment NTEXT,
	CONSTRAINT fk_StudentAttend FOREIGN KEY (Student) REFERENCES Students(StudentID),
	FOREIGN KEY ([Session]) REFERENCES [Sessions](SessionID),
	CONSTRAINT uk_Student_Session UNIQUE (Student, [Session])
);

/* 
	Inserting data
*/

INSERT INTO Users VALUES
('sa@mail.com','12345','admin'),
('sonnt69@fe.edu.vn','12345','sonnt'),
('bantq@fe.edu.vn','12345','bantq');

INSERT INTO Instructors VALUES
('sonnt5','Son','Tung','Ngo','sonnt69@fe.edu.vn'),
('bantq','Ban','Quy','Tran','bantq@fe.edu.vn'),
('tuanvm2','Tuan','Minh','Vuong',NULL),
('hailt','Hai','Thanh','Le',NULL);

INSERT INTO Courses VALUES
('PRJ301','Java Web Application Development'),
('SWP391','Application Development Project'),
('IOT102','Internet of Things'),
('LAB211','OOP with Java Lab'),
('DBI202','Introduction to Databases'),
('CSD201','Data Structures and Algorithms'),
('PRO192','Object-Oriented Programming'),
('PRN221','Advanced Cross-Platform Application Programming With .NET'),
('PRU211m','C# Programming and Unity'),
('SWD392','Software Architecture and Design'),
('PRM392','Mobile Programming');

INSERT INTO Students VALUES
('HE150057',N'Anh',N'Nhật',N'Nguyễn'),
('HE151095',N'Minh',N'Đức',N'Nguyễn'),
('HE153206',N'Nam',N'Duy',N'Quách'),
('HE160694',N'Duy',N'Đình',N'Vũ'),
('HE161357',N'Tây',N'Trường',N'Nguyễn'),
('HE161795',N'Minh',N'Thành',N'Dương'),
('HE163146',N'Hùng',N'Việt',N'Bùi'),
('HE164035',N'Anh',N'Tiến',N'Nguyễn'),
('HE170051',N'Tâm',N'Minh',N'Vũ'),
('HE170245',N'Long',N'Hoàng',N'Bùi'),
('HE170422',N'Phúc',N'Duy',N'Vũ'),
('HE170428',N'Huy',N'Quang',N'Trần'),
('HE170444',N'Hiếu',N'Đức',N'Lê'),
('HE170533',N'Lợi',N'Quang',N'Nguyễn'),
('HE170842',N'Hậu',N'Thanh',N'Nguyễn'),
('HE170863',N'Huyền',N'Thị Khánh',N'Nguyễn'),
('HE170907',N'Giang',N'Trường',N'Phạm'),
('HE170996',N'Đức',N'Minh',N'Khiếu'),
('HE171071',N'Nhất',N'Ngọc',N'Vũ'),
('HE171073',N'Phương',N'Mai',N'Hoàng'),
('HE171162',N'Dũng',N'Tiến',N'Bùi'),
('HE171442',N'Minh',N'Công Quang',N'Văn'),
('HE171482',N'Mạnh',N'Tiến',N'Đinh'),
('HE171578',N'Hải',N'Nam',N'Kiều'),
('HE171687',N'Phúc',N'Lâm',N'Doãn'),
('HE171851',N'Minh',N'Đức',N'Nguyễn'),
('HE171865',N'Hiếu',N'Quang',N'Nguyễn'),
('HE176182',N'Anh',N'Việt',N'Phan'),
('HE176697',N'Tú',N'Thị Cẩm',N'Nguyễn'),
('HE176751',N'Đạt',N'Quốc',N'Nguyễn'),
('SE03520',N'Bala',N'Peter',N'Shunom'),
('SE04495',N'Maurice',N'Obasi',N'Ntui');

INSERT INTO TimeSlots VALUES
('1P','7:30:00','09:50:00'),
('2P','10:00:00','12:20:00'),
('3P','12:50:00','15:10:00'),
('4P','15:20:00','17:40:00'),
('1','07:30:00','09:00:00'),
('2','09:10:00','10:40:00'),
('3','10:50:00','12:20:00'),
('4','12:50:00','14:20:00'),
('5','14:30:00','16:00:00'),
('6','16:10:00','17:40:00');

INSERT INTO Rooms (RoomName) VALUES
('BE-301'),
('BE-303'),
('BE-305'),
('BE-306'),
('BE-307'),
('BE-308'),
('DE-305'),
('DE-306'),
('DE-307'),
('DE-308'),
('AL-203R'),
('AL-204R'),
('AL-203L'),
('AL-204L');

INSERT INTO Groups (GroupName, [CourseID]) VALUES 
('IOT1702',1),
('SE1723',7),
('ISE_1723',1),
('SE1723',3),
('SE1726',5);

INSERT INTO [Join] (StudentID, GroupID) VALUES
('HE171073',3),
('HE171162',3),
('HE171442',3),
('HE171482',3),
('HE171578',3),
('HE171687',3),
('HE171851',3),
('HE150057',3),
('HE151095',3),
('HE153206',3),
('HE160694',3),
('HE161357',3),
('HE161795',3),
('HE163146',3);

INSERT INTO [Sessions] (SessionName, Instructor, [Group], TimeSlot, Room, InstructorStatus, ConductDate) VALUES
('Create a Servlet', 'sonnt5',3,'1P',2,0,'2023-02-06'),
('Sessions and Cookies','sonnt5',3,'3P',2,0,'2023-02-10'),
('Workshop 1', 'sonnt5',3,'1P',2,0,'2023-02-14'),
('Introduction to Databases', 'bantq',1,'2P',2,0,'2023-03-16');
/*
('Introduction to Databases', 'bantq',4,'2P','DE-307',1,'2023-02-12'),

('JSP Authentication',  'sonnt5',1,'2P','BE-301',1,'2023-02-16')
*/

INSERT INTO [AssignTo] (InstructorID, GroupID) VALUES
('sonnt5',3);

/*
INSERT INTO [Attend] VALUES
('HE171162',1,1,'2023-02-24 13:00:25',''),
('HE170245',1,1,'2023-02-24 13:00:25',''),
('HE171071',1,0,'2023-02-24 13:00:25',''),
('HE171162',2,1,'2023-02-24 13:00:25',''),
('HE170245',2,0,'2023-02-24 13:00:25',''),
('HE171071',2,0,'2023-02-24 13:00:25','');
*/










