package com.epam.brest.taskproject.domain;

/**
 * Created by alesya on 17.11.14.
 */
public class AutomobileSummary {

    private Automobile automobile;
    private Double sumDistance;

    public AutomobileSummary() {
    }

    public AutomobileSummary(Automobile automobile, Double sumDistance) {
        this.automobile = automobile;
        this.sumDistance = sumDistance;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Double getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public Double getSumFuel(){
        if (sumDistance==null)
            return 0D;
        if (automobile.getFuelRate()==null)
            return 0D;
        return sumDistance* automobile.getFuelRate();
    }
}
