--Grayson Closs 2023-02-08
--Students table for the Assignment 2 for WEBD4201
--The script should be run against a webd4201_db on port 5432 in pgAdmin.
--webd4201_db should be owned by a user with the name db_user with a password of webd4201_password 


DROP TABLE IF EXISTS students;
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


SELECT * FROM students;