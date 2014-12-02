create table if not exists Event(

	ID INT NOT NULL auto_increment,
    UserID INT NOT NULL,
    IsOwner TINYINT(1) DEFAULT 1,
    StartTime varchar(100),
    EndTime varchar(100),
    Texts varchar(100),
    EValue varchar(100),
	Resources VARCHAR(100),
    primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8; 
 
