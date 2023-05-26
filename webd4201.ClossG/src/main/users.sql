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

