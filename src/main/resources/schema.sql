CREATE TABLE IF NOT EXISTS Run (
   id INT AUTO_INCREMENT,
   title varchar(250) NOT NULL,
   started_on timestamp NOT NULL,
   completed_on timestamp NOT NULL,
   miles INT NOT NULL,
   location varchar(10) NOT NULL,
   PRIMARY KEY (id)
);

INSERT INTO Run(title,started_on,completed_on,miles,location)
VALUES ('Monday Morning Run',CURRENT_TIMESTAMP(),TIMESTAMPADD('minute',30,CURRENT_TIMESTAMP()),3,'INDOOR');

INSERT INTO Run(title,started_on,completed_on,miles,location)
VALUES ('Tuesday Evening Run',CURRENT_TIMESTAMP(),TIMESTAMPADD('minute',60,CURRENT_TIMESTAMP()),6,'INDOOR');