select a.automobileid,make,number,fuelrate, journeyid,origin_destination,journey_date,distance
from AUTOMOBILE as a join JOURNEY as j on a.automobileid = j.automobileid 
where automobileid = ?