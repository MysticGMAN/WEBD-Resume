--Grayson Closs 2023-02-08
--Students table for the Assignment 2 for WEBD4201
--The script should be run against a webd4201_db on port 5432 in pgAdmin.
--webd4201_db should be owned by a user with the name db_user with a password of webd4201_password 


DROP TABLE IF EXISTS faculty;
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


SELECT * FROM faculty;