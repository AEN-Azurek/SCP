package com.scp.CalculatorPlus.service.calculation;

import org.apache.commons.math3.fraction.BigFraction;
import org.junit.jupiter.api.Test;

import static com.scp.CalculatorPlus.service.calculation.PowerCalculations.calculateAdjustedPowerConsumption;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerCalculationsTests {

    @Test
    public void calculateAdjustedPowerConsumption_ShouldFailClockSpeed0() {
        int defaultPowerUsage = 50;
        BigFraction clockSpeed = new BigFraction(0);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(-1, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldFailClockSpeed251() {
        int defaultPowerUsage = 50;
        BigFraction clockSpeed = new BigFraction(3);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(-1, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldFailDefaultPowerUsage() {
        int defaultPowerUsage = -1;
        BigFraction clockSpeed = new BigFraction(1);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(-1, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive4() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(1);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(4, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive0_19() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(0.1);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(0.19, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive1_6() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(0.5);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(1.6, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive6_84() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(1.5);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(6.84, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive10() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(2.0);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(10, powerUsage);
    }

    @Test
    public void calculateAdjustedPowerConsumption_ShouldGive13_43() {
        int defaultPowerUsage = 4;
        BigFraction clockSpeed = new BigFraction(2.5);

        double powerUsage = calculateAdjustedPowerConsumption(defaultPowerUsage, clockSpeed);

        assertEquals(13.43, powerUsage);
    }
}
