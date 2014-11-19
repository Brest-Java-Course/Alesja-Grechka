package com.epam.brest.taskproject.domain;

import java.util.Date;

/**
 * Created by alesya on 17.11.14.
 */
public class Journey {
    private Long id;
    private Automobile automobile;
    private Date date;
    private String originDestination;
    private Double distance;

    public Journey() {
    }

    public Journey(Long id, Automobile automobile, Date date, String originDestination, Double distance) {
        this.id = id;
        this.automobile = automobile;
        this.date = date;
        this.originDestination = originDestination;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOriginDestination() {
        return originDestination;
    }

    public void setOriginDestination(String originDestination) {
        this.originDestination = originDestination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journey journey = (Journey) o;

        if (automobile != null ? !automobile.equals(journey.automobile) : journey.automobile != null) return false;
        if (date != null ? !date.equals(journey.date) : journey.date != null) return false;
        if (distance != null ? !distance.equals(journey.distance) : journey.distance != null) return false;
        if (id != null ? !id.equals(journey.id) : journey.id != null) return false;
        if (originDestination != null ? !originDestination.equals(journey.originDestination) : journey.originDestination != null)
            return false;

        return true;
    }

}

