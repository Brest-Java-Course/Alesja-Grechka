DELETE FROM JOURNEY;
DELETE FROM AUTOMOBILE;
INSERT INTO AUTOMOBILE (automobileid,make,number,fuelrate) VALUES (1,'audi','0013-ih1', 6.2);
INSERT INTO AUTOMOBILE (automobileid,make,number,fuelrate) VALUES (2,'alfaromeo','4707-ek1', 5.1);
INSERT INTO AUTOMOBILE (automobileid,make,number,fuelrate) VALUES (3,'ford','2101-it7', 8.1);

INSERT INTO JOURNEY (journeyid,automobileid,origin_destination,journey_date,distance) 
VALUES (1,1,'kobrin-brest', '2014-10-01', 50.0);
INSERT INTO JOURNEY (journeyid,automobileid,origin_destination,journey_date,distance) 
VALUES (2,1,'brest-warsaw', '2014-10-21', 200.0);
INSERT INTO JOURNEY (journeyid,automobileid,origin_destination,journey_date,distance) 
VALUES (3,2,'brest-minsk', '2014-09-15', 300.0);