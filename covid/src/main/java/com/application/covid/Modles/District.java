package com.application.covid.Modles;

import org.springframework.stereotype.Component;

@Component
public class District {

    private String state;
    private String dist;
    private int activeCase;
    private int confirmedCase;
    private int deceasedCase;
    private int recoveredCase;

    public District() {
    }

    public District(String state, String dist, int activeCase, int confirmedCase, int deceasedCase, int recoveredCase) {
        this.state = state;
        this.dist = dist;
        this.activeCase = activeCase;
        this.confirmedCase = confirmedCase;
        this.deceasedCase = deceasedCase;
        this.recoveredCase = recoveredCase;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public int getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(int activeCase) {
        this.activeCase = activeCase;
    }

    public int getConfirmedCase() {
        return confirmedCase;
    }

    public void setConfirmedCase(int confirmedCase) {
        this.confirmedCase = confirmedCase;
    }

    public int getDeceasedCase() {
        return deceasedCase;
    }

    public void setDeceasedCase(int deceasedCase) {
        this.deceasedCase = deceasedCase;
    }

    public int getRecoveredCase() {
        return recoveredCase;
    }

    public void setRecoveredCase(int recoveredCase) {
        this.recoveredCase = recoveredCase;
    }
}