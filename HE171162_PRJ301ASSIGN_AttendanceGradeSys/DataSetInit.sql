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
('PRM392','Mobile Programming'),
('AIL303m','Machine Learning');

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
('SE04495',N'Maurice',N'Obasi',N'Ntui'),
('HE163767',N'Huy',N'Quang',N'Nguyễn'),
('HE170305',N'Phương',N'Thị Mai',N'Phan'),
('HE170418',N'Dương',N'Hải',N'Nguyễn'),
('HE170472',N'Huy',N'Đỗ',N'Lê'),
('HE170594',N'Khải',N'Đỗ',N'Hoàng'),
('HE170596',N'Đăng',N'Hải',N'Nguyễn'),
('HE170696',N'Đạt',N'Tất Thành',N'Nguyễn'),
('HE170744',N'Nam',N'Thành',N'Dương'),
('HE170746',N'Hoàn',N'Đức',N'Lê'),
('HE170769',N'Tuấn',N'Huy',N'Nguyễn'),
('HE170770',N'Kiên',N'Trung',N'Hoàng'),
('HE170776',N'Minh',N'Quang',N'Bùi'),
('HE170952',N'Minh',N'Quang',N'Bùi'),
('HE171047',N'Thành',N'Bá',N'Trần'),
('HE171106',N'Huy',N'Xuân',N'Nguyễn'),
('HE171219',N'Huy',N'Nhật',N'Nguyễn'),
('HE171234',N'Đạt',N'Quốc',N'Đinh'),
('HE171244',N'Minh',N'Đức',N'Nguyễn'),
('HE171252',N'Hoàng',N'Minh',N'Dương'),
('HE171257',N'Quang',N'Minh',N'Chu'),
('HE171281',N'Huy',N'Đức',N'Nguyễn'),
('HE171584',N'Hưng',N'Thế',N'Nguyễn'),
('HE171671',N'Khánh',N'Quốc',N'Dương'),
('HE171708',N'Anh',N'Quang',N'Đinh'),
('HE171929',N'Châu',N'Minh',N'Nguyễn'),
('HE172821',N'Anh',N'Hoàng Giang',N'Nguyễn'),
('HE173502',N'Minh',N'Duy Nhật',N'Khuất'),
('HE176220',N'Đức',N'Anh',N'Hoàng'),
('HE179008',N'Dũng',N'Việt',N'Hồ'),
('HE140494',N'Dương',N'Hoàng',N'Lê'),
('HE141029',N'Long',N'Kim Hoàng',N'Dương'),
('HE150203',N'Tùng',N'Sơn',N'Đỗ'),
('HE150248',N'Kiên',N'Trung',N'Phan'),
('HE150366',N'Sơn',N'Hoài',N'Nguyễn'),
('HE150377',N'Hoàng',N'Việt',N'Nguyễn'),
('HE150632',N'Được',N'Quốc',N'Nguyễn'),
('HE150726',N'Anh',N'Hữu Tiến',N'Nguyễn'),
('HE150767',N'Việt',N'Tuấn',N'Phan'),
('HE150771',N'Nam',N'Phương',N'Nguyễn'),
('HE150901',N'Đức',N'Minh',N'Nguyễn'),
('HE151191',N'Đại',N'Văn',N'Lương'),
('HE151375',N'Đạt',N'Hữu',N'Lê'),
('HE151412',N'Phương',N'Thị',N'Nguyễn'),
('HE151445',N'Quang',N'Minh',N'Từ'),
('HE151472',N'Hiếu',N'Minh',N'Phạm'),
('HE153014',N'Trai',N'Văn',N'Chu'),
('HE153016',N'Hưng',N'Tiến',N'Vũ'),
('HE153058',N'Thái',N'Quang',N'Đinh'),
('HE153072',N'Tịnh',N'Danh',N'Lê'),
('HE153199',N'Hiếu',N'Ngọc',N'Trần'),
('HE153275',N'Đăng',N'Quý',N'Bùi'),
('HE153321',N'San',N'Xuân Tấn',N'Nguyễn'),
('HE153570',N'Long',N'Đức',N'Lê'),
('HE153601',N'Vũ',N'Tuấn',N'Vi'),
('HE153727',N'Anh',N'Ngọc',N'Phạm'),
('HE160485',N'Dương',N'Hải',N'Phạm'),
('HE163009',N'Trang',N'Thùy',N'Trần'),
('HE163336',N'Minh',N'Thiên',N'Nguyễn'),
('HS153257',N'Thành',N'Phúc',N'Vũ'),
('SE05736',N'Hòa',N'Trung',N'Tiêu');


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
('SE1615-NET',8),
('SE1723',7),
('ISE_1723',1),
('SE1723',3),
('AI1703',12);

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
('HE163146',3),
('HE163767',5),
('HE170305',5),
('HE170418',5),
('HE170472',5),
('HE170594',5),
('HE170596',5),
('HE170696',5),
('HE170744',5),
('HE170746',5),
('HE170769',5),
('HE170770',5),
('HE170776',5),
('HE170952',5),
('HE171047',5),
('HE171106',5),
('HE171219',5),
('HE171234',5),
('HE171244',5),
('HE171252',5),
('HE171257',5),
('HE171281',5),
('HE171584',5),
('HE171671',5),
('HE171708',5),
('HE171929',5),
('HE172821',5),
('HE173502',5),
('HE176220',5),
('HE179008',5),
('HE140494',1),
('HE141029',1),
('HE150203',1),
('HE150248',1),
('HE150366',1),
('HE150377',1),
('HE150632',1),
('HE150726',1),
('HE150767',1),
('HE150771',1),
('HE150901',1),
('HE151191',1),
('HE151375',1),
('HE151412',1),
('HE151445',1),
('HE151472',1),
('HE153014',1),
('HE153016',1),
('HE153058',1),
('HE153072',1),
('HE153199',1),
('HE153275',1),
('HE153321',1),
('HE153570',1),
('HE153601',1),
('HE153727',1),
('HE160485',1),
('HE163009',1),
('HE163336',1),
('HS153257',1),
('SE05736',1);


INSERT INTO [Sessions] (SessionName, Instructor, [Group], TimeSlot, Room, InstructorStatus, ConductDate) VALUES
('Sessions & Cookies','sonnt5',3,'1P',2,0,'2023-03-20'),
('Workshop 1','sonnt5',3,'2P',2,0,'2023-03-22'),
('Interface in C#', 'sonnt5',1,'3P',8,0,'2023-03-21'),
('Extending Classes', 'sonnt5',1,'4P',8,0,'2023-03-20'),
('PyTorch Basics', 'bantq',5,'2P',3,0,'2023-03-20'),
('PyTorch Basics 2', 'bantq',5,'1P',3,0,'2023-03-21');
/*
('Introduction to Databases', 'bantq',4,'2P','DE-307',1,'2023-02-12'),

('JSP Authentication',  'sonnt5',1,'2P','BE-301',1,'2023-02-16')
*/

INSERT INTO [AssignTo] (InstructorID, GroupID) VALUES
('sonnt5',3),
('sonnt5',1),
('bantq',5);
/*
INSERT INTO [Attend] VALUES
('HE171162',1,1,'2023-02-24 13:00:25',''),
('HE170245',1,1,'2023-02-24 13:00:25',''),
('HE171071',1,0,'2023-02-24 13:00:25',''),
('HE171162',2,1,'2023-02-24 13:00:25',''),
('HE170245',2,0,'2023-02-24 13:00:25',''),
('HE171071',2,0,'2023-02-24 13:00:25','');
*/










