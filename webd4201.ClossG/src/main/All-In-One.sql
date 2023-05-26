--Grayson Closs 2023-02-08
--Users table for the Assignment 2 for WEBD4201
--The script should be run against a webd4201_db on port 5432 in pgAdmin.
--webd4201_db should be owned by a user with the name db_user with a password of webd4201_password 
CREATE EXTENSION IF NOT EXISTS pgcrypto; 

DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS users;
CREATE TABLE users(
	--Id PRIMARY KEY DEFAULT nextval('users_id_seq'),
	CollegeId INT PRIMARY KEY UNIQUE,  
	Password VARCHAR(40) NOT NULL,
	FirstName VARCHAR(128) NOT NULL,
	LastName VARCHAR(128) NOT NULL,
	EmailAddress VARCHAR(256) UNIQUE NOT NUll,
	LastAccess DATE,
	EnrolDate TIMESTAMP NOT NULL,
	Enabled BOOLEAN NOT NULL,
	Type CHAR(2) NOT NULL
);

ALTER TABLE users OWNER TO webd4201_admin;
INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type) 
VALUES (100597686, encode(digest('100597686', 'sha1'), 'hex'), 'Grayson', 'Closs', 'grayson.closs@dcmail.ca', NULL, '2023-02-08 16:55:00', TRUE, 's');
INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
VALUES(100754614, encode(digest('Wholenewchapter1', 'sha1'), 'hex'), 'Conner', 'Tamane', 'conner.tamane@dcmail.ca', NULL, '2023-02-07 18:43:00', TRUE, 's');
INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
VALUES(100111111, encode(digest('password', 'sha1'), 'hex'), 'Mike', 'Jones', 'mike.jones@dcmail.ca', NULL, '2015-09-11', TRUE, 's');

INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type) 
VALUES (200666777, encode(digest('100666777', 'sha1'), 'hex'), 'Ryan', 'Longnecker', 'ryan.longnecker@durhamcollege.ca', NULL, '1995-02-08 16:55:00', TRUE, 'f');
INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
VALUES(200420699, encode(digest('fightclub', 'sha1'), 'hex'), 'Tyler', 'Durden', 'tyler.durden@durhamcollege.ca', NULL, '2023-02-07 18:43:00', TRUE, 'f');
INSERT INTO users(CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type)
VALUES(111111001, encode(digest('password', 'sha1'), 'hex'), 'Jones', 'Mike', 'jones.mike@durhamcollege.ca', NULL, '2015-09-11', TRUE, 'f');


--Grayson Closs 2023-02-08
--Students table for the Assignment 2 for WEBD4201
--The script should be run against a webd4201_db on port 5432 in pgAdmin.
--webd4201_db should be owned by a user with the name db_user with a password of webd4201_password 



CREATE TABLE students(
	--Id PRIMARY KEY DEFAULT nextval('users_id_seq'),
	id SERIAL PRIMARY KEY,
	CollegeId INT NOT NULL,  
	ProgramCode VARCHAR(5) NOT NULL,
	ProgramDescription VARCHAR(256) NOT NUll,
	CurrentYear INT NOT NULL,
	FOREIGN KEY (CollegeId) REFERENCES users(CollegeId)
);

ALTER TABLE students OWNER TO webd4201_admin;
INSERT INTO students(CollegeId, ProgramCode, ProgramDescription, CurrentYear) 
VALUES (100597686, 'CPGA', 'Computer Programming & Analysis', 2);
INSERT INTO students(CollegeId, ProgramCode, ProgramDescription, CurrentYear)
VALUES (100754614, 'CPPG', 'Computer Programming', 2);
INSERT INTO students(CollegeId, ProgramCode, ProgramDescription, CurrentYear)
VALUES (100111111, 'CPGA', 'Computer Programming & Analysis', 3);


--Grayson Closs 2023-02-08
--Students table for the Assignment 2 for WEBD4201
--The script should be run against a webd4201_db on port 5432 in pgAdmin.
--webd4201_db should be owned by a user with the name db_user with a password of webd4201_password 



CREATE TABLE faculty(
	--Id PRIMARY KEY DEFAULT nextval('users_id_seq'),
	id SERIAL PRIMARY KEY,
	CollegeId INT NOT NULL,  
	SchoolCode VARCHAR(5) NOT NULL,
	SchoolDescription VARCHAR(256) NOT NUll,
	Office VARCHAR(6) NOT NULL,
	Extension int NOT NULL,
	FOREIGN KEY (CollegeId) REFERENCES users(CollegeId)
);

ALTER TABLE faculty OWNER TO webd4201_admin;
INSERT INTO faculty(CollegeId, SchoolCode, SchoolDescription, Office, Extension) 
VALUES (200666777, 'SET', 'School of Science, Engineering & Information Technology', 'C-324', 6345);
INSERT INTO faculty(CollegeId, SchoolCode, SchoolDescription, Office, Extension)
VALUES (200420699, 'SET', 'School of Science, Engineering & Information Technology', 'SW-100', 6969);
INSERT INTO faculty(CollegeId, SchoolCode, SchoolDescription, Office, Extension)
VALUES (111111001, 'SET', 'School of Science, Engineering & Information Technology', 'H-420', 4209);


SELECT * FROM users, faculty, students;