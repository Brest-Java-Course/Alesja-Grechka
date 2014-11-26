package com.epam.brest.taskproject.domain;

/**
 * Created by alesya on 17.11.14.
 */
public class AutomobileSummary {

    private Automobile automobile;
    private Double sumDistance;
    private Double sumFuel;

    public AutomobileSummary() {
    }

    public AutomobileSummary(Automobile automobile, Double sumDistance) {
        this.automobile = automobile;
        this.sumDistance = sumDistance;
        if (sumDistance == null)
            this.sumFuel = 0D;
        if (automobile.getFuelRate() == null)
            this.sumFuel = 0D;
        this.sumFuel =  sumDistance* automobile.getFuelRate();
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public Double getSumDistance() {
        return sumDistance;
    }

    public Double getSumFuel(){
        return sumFuel;
    }
}
