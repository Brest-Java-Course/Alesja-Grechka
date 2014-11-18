package com.epam.brest.taskproject.domain;

/**
 * Created by alesya on 17.11.14.
 */
public class TransportSummary {
    private Automobile transport;
    private Double sumDistance;

    public TransportSummary() {
    }

    public TransportSummary(Automobile transport, Double sumDistance) {
        this.transport = transport;
        this.sumDistance = sumDistance;
    }

    public Automobile getTransport() {
        return transport;
    }

    public void setTransport(Automobile transport) {
        this.transport = transport;
    }

    public Double getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public Double getSumFuel(){
        return sumDistance*transport.getFuelRate();
    }
}
