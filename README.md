Hungry-in-Oxford
================

### DB Schema so Craig doesn't have to look in his email anymore ###

    CREATE TABLE Restaurant (
    	Name VARCHAR(50) NOT NULL,
    	Type VARCHAR(50),
    	Address VARCHAR(50),
    	Telephone VARCHAR(50),
    	Delievery BOOLEAN,
    	PRIMARY KEY (Name)
    );

    CREATE TABLE FoodItems (
    	Name VARCHAR(50),
    	FId INT NOT NULL, 
    	RName VARCHAR(50) NOT NULL,
    	Price DECIMAL(10,2),
    	PRIMARY KEY (Fid),
    	FOREIGN KEY (Rname) REFERENCES Restaurant (Name)
    );
    
    CREATE TABLE SpecialtyDrinks (
    	Name VARCHAR(50),
    	DId INT NOT NULL, 
    	RName VARCHAR(50) NOT NULL,
    	Price DECIMAL(10,2),
    	PRIMARY KEY (Did),
    	FOREIGN KEY (Rname) REFERENCES Restaurant (Name)
    );
    
    CREATE TABLE NutritionalValues (
    	FId INT NOT NULL,
    	Calories INT,
    	Fat INT,
    	Sugar INT, 
    	Sodium INT,
    	Carbs INT,
    	PRIMARY KEY (FId),
    	FOREIGN KEY (FId) REFERENCES FoodItems (FId)
    );
    
    CREATE TABLE Availability (
    	RName VARCHAR(50) NOT NULL,
    	DayOpen VARCHAR(50) NOT NULL,
    	TimeOpen VARCHAR(50),
    	TimeClose VARCHAR(50),
    	PRIMARY KEY (RName, DayOpen),
    	FOREIGN KEY (Rname) REFERENCES Restaurant (Name)
    );
    
    
http://www.javasoft.de/synthetica/themes/
