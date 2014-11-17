package com.epam.brest.taskproject.domain;

import java.util.List;

/**
 * Created by alesya on 17.11.14.
 */
public class Transport {
    private Long id;
    private String make;
    private String number;
    private Double fuelRate;

    public Transport() {
    }

    public Transport(Long id, String make, String number, Double fuelRate) {
        this.id = id;
        this.make = make;
        this.number = number;
        this.fuelRate = fuelRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(Double fuelRate) {
        this.fuelRate = fuelRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (fuelRate != null ? !fuelRate.equals(transport.fuelRate) : transport.fuelRate != null) return false;
        if (id != null ? !id.equals(transport.id) : transport.id != null) return false;
        if (make != null ? !make.equals(transport.make) : transport.make != null) return false;
        if (number != null ? !number.equals(transport.number) : transport.number != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Transport: {" +
                "id=" + id +
                ", make='" + make +
                ", number='" + number +
                ", fuelRate=" + fuelRate +
                '}';
    }

}
