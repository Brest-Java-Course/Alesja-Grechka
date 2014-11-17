package com.epam.brest.taskproject.domain;

/**
 * Created by alesya on 17.11.14.
 */
public class TransportSummary {
    private Transport transport;
    private Double sumDistance;

    public TransportSummary() {
    }

    public TransportSummary(Transport transport, Double sumDistance) {
        this.transport = transport;
        this.sumDistance = sumDistance;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
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
