create table if not exists EventTable(

	id INT NOT NULL auto_increment,
    UserID INT NOT NULL,
    Title varchar(100) not null,
    Tag varchar(100),
    DateStr varchar(100),
    TimeStr varchar(100),
    Address varchar(100),
    primary key (id)
)
    
