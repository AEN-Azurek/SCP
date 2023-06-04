package com.scp.CalculatorPlus.constants.models.buildings;

import com.scp.CalculatorPlus.model.buildings.Attribute;

public class AttributeConstants {

    public static final String POWER_CONSUMPTION_STRING = "power_consumption";

    public static final String POWER_PRODUCTION_STRING = "power_production";

    public static final String VARIABLE_MIN_POWER_CONSUMPTION_STRING = "variable_min_power_consumption";

    public static final String VARIABLE_MIN_POWER_PRODUCTION_STRING = "variable_min_power_production";

    public static final Attribute POWER_CONSUMPTION = new Attribute(1L, POWER_CONSUMPTION_STRING);

    public static final Attribute POWER_PRODUCTION = new Attribute(2L, POWER_PRODUCTION_STRING);

    public static final Attribute VARIABLE_MIN_POWER_CONSUMPTION = new Attribute(3L, VARIABLE_MIN_POWER_CONSUMPTION_STRING);

    public static final Attribute VARIABLE_MIN_POWER_PRODUCTION = new Attribute(4L, VARIABLE_MIN_POWER_PRODUCTION_STRING);
}
