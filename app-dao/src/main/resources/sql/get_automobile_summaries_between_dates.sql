select automobileid, make, number,fuelrate,sum(distance) from
(select a.automobileid,make,number,fuelrate, distance, journey_date
from AUTOMOBILE as a LEFT OUTER JOIN JOURNEY as j 
on (a.automobileid = j.automobileid and journey_date between :date1 and :date2)
) group by automobileid, make, number,fuelrate