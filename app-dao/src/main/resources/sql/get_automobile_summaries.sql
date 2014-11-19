select automobileid, make, number,fuelrate,sum(distance) as sumdistance from
(select a.automobileid,make,number,fuelrate, distance
from AUTOMOBILE as a left outer join JOURNEY as j on a.automobileid = j.automobileid)
group by automobileid, make, number,fuelrate