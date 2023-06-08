package com.scp.CalculatorPlus.service.calculation;

import org.apache.commons.math3.fraction.BigFraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PowerCalculations {

    static Logger logger = LoggerFactory.getLogger(PowerCalculations.class);

    private static final BigFraction MIN_CLOCK_SPEED = new BigFraction(0.01);

    private static final BigFraction MAX_CLOCK_SPEED = new BigFraction(2.5);

    private static final double POWER_EXP = Math.log(2.5) / Math.log(2);

    /**
     * Calculates the overclocked or underclocked power consumption based on the power usage and speed
     *
     * @param defaultPowerUsage - Base power consumption of building
     * @param clockSpeed - Adjusted clock speed
     * @return The total power consumption after under/overclock
     */
    public static BigDecimal calculateAdjustedPowerConsumption(int defaultPowerUsage, BigFraction clockSpeed) {
        if (clockSpeed.compareTo(MIN_CLOCK_SPEED) < 0 || clockSpeed.compareTo(MAX_CLOCK_SPEED) > 0) {
            logger.info("Clock speed must be between 0.01 and 2.50");
            return new BigDecimal(-1);
        }

        if (defaultPowerUsage < 0) {
            logger.info("Invalid power usage");
            return new BigDecimal(-1);
        }

        BigDecimal value = new BigDecimal(defaultPowerUsage * clockSpeed.pow(POWER_EXP));

        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
