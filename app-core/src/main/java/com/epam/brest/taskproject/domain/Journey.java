package com.epam.brest.taskproject.domain;

import java.util.Date;

/**
 * Created by alesya on 17.11.14.
 */
public class Journey {
    private Long id;
    private String originDestination;
    private Transport transport;
    private Double distance;
    private Date date;

    public Journey() {
    }

    public Journey(Long id, String originDestination, Transport transport, Double distance, Date date) {
        this.id = id;
        this.originDestination = originDestination;
        this.transport = transport;
        this.distance = distance;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginDestination() {
        return originDestination;
    }

    public void setOriginDestination(String originDestination) {
        this.originDestination = originDestination;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", originDestination='" + originDestination + '\'' +
                ", transport=" + transport +
                ", distance=" + distance +
                ", date=" + date +
                '}';
    }


}
