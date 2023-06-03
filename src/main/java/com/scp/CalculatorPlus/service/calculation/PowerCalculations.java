package com.scp.CalculatorPlus.service.calculation;

import org.apache.commons.math3.fraction.BigFraction;

public class PowerCalculations {

    public BigFraction calculateOverclockedPowerConsumption() {
        // power usage = initial power usage * (clock speed / 100)^log2(2.5)
        // clock speed can have at most 4 decimal places, and must be between 1 and 250.
        // log2(2.5) means that to double production speed, we need 2.5x more power
        return BigFraction.ONE;
    }
}
