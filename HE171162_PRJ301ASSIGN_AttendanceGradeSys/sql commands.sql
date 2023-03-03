/*
Gets schedule at certain datetime (week schedule)
*/
SELECT g.GroupName, g.CourseID, ts.StartTime, ts.EndTime 
FROM [Sessions] s INNER JOIN Groups g ON s.[Group] = g.GroupID INNER JOIN TimeSlots ts on s.TimeSlot = ts.SlotID
WHERE s.Instructor = 'sonnt5' AND s.ConductDate BETWEEN '2023-02-05' AND '2023-02-12';

/*
Get student attendance status of a class
*/
SELECT s.StudentID,(s.LastName + ' ' + s.MiddleName + ' ' + s.FirstName) AS FullName, a.[Status], ss.ConductDate FROM Attend a INNER JOIN Students s ON a.Student = s.StudentID INNER JOIN [Sessions] ss ON a.[Session] = ss.SessionID
WHERE ss.[Group] = 1

/*
Returns info for attendance check
*/
SELECT s.StudentID,(s.LastName + ' ' + s.MiddleName + ' ' + s.FirstName) AS FullName, a.[Status], a.InstructorComment 
FROM Attend a INNER JOIN Students s ON a.Student = s.StudentID
WHERE a.[Session] = 1

/*
Get Group information for drop down?
*/

SELECT g.GroupName FROM [Groups] g

/*
Get students via such classes
*/

SELECT s.StudentID, s.FirstName, s.MiddleName, s.LastName 
FROM Students s INNER JOIN [Join] j ON s.StudentID = j.StudentID INNER JOIN Groups g ON j.GroupID = g.GroupID
WHERE g.GroupName = 'ISE_1723' AND g.CourseID = 'PRJ301'
ORDER BY s.StudentID