DROP TABLE User;


CREATE TABLE User
(
	ID INT NOT NULL AUTO_INCREMENT,
	FirstName VARCHAR(40),
	LastName VARCHAR(40),
	UserName VARCHAR(40) NOT NULL UNIQUE,
	UserEmail VARCHAR(100) NOT NULL UNIQUE,
	Password VARCHAR(100) NOT NULL,
	Role VARCHAR(20) NOT NULL,
	PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Insert Into User (FirstName, LastName, UserName, UserEmail, Password, Role) 
VALUES ("Hongbing", "Shao", "hongbing","hongbing.shao@gmail.com", "hongbing","STUDENT");

Insert Into User (FirstName, LastName, UserName, UserEmail, Password, Role) 
VALUES ("Apil", "Tamang", "apil","apil.tamang@gmail.com", "apil","STUDENT");

Insert Into User (FirstName, LastName, UserName, UserEmail, Password, Role) 
VALUES ("Tim", "Kennedy", "tim","tkennedy0617@gmail.com", "tim","STUDENT");

Insert Into User (FirstName, LastName, UserName, UserEmail, Password, Role) 
VALUES ("Bobby", "Writtenberry", "bobby","bobby.writtenberry@gmail.com", "bobby","STUDENT");