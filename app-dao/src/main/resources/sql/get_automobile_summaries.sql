select automobileid, make, number,fuelrate,sum(distance) from
(select a.automobileid,make,number,fuelrate, distance
from AUTOMOBILE as a LEFT OUTER JOIN JOURNEY as j on a.automobileid = j.automobileid)
group by automobileid, make, number,fuelrate