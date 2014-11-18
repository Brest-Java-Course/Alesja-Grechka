select journeyid, automobileid, origin_destination, journey_date, distance
from JOURNEY where journey_date between :date1 and :date2