select journeyid,automobileid,origin_destination,journey_date,distance from JOURNEY
where automobileid= :automobileid and journey_date between :date1 and :date2