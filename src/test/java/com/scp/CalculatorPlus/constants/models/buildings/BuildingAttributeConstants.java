package com.scp.CalculatorPlus.constants.models.buildings;

import com.scp.CalculatorPlus.model.buildings.BuildingAttribute;

import java.util.ArrayList;
import java.util.List;

import static com.scp.CalculatorPlus.constants.models.buildings.AttributeConstants.*;
import static com.scp.CalculatorPlus.constants.models.buildings.BuildingConstants.*;

public class BuildingAttributeConstants {

    public static final List<BuildingAttribute> ASSEMBLER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(1, ASSEMBLER, POWER_CONSUMPTION, "15"));
    }};
    public static final List<BuildingAttribute> BLENDER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(2, BLENDER, POWER_CONSUMPTION, "75"));
    }};
    public static final List<BuildingAttribute> CONSTRUCTOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(3, CONSTRUCTOR, POWER_CONSUMPTION, "4"));
    }};
    public static final List<BuildingAttribute> FOUNDRY_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(4, FOUNDRY, POWER_CONSUMPTION, "16"));
    }};
    public static final List<BuildingAttribute> MANUFACTURER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(5, MANUFACTURER, POWER_CONSUMPTION, "55"));
    }};
    public static final List<BuildingAttribute> MINER_MK1_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(6, MINER_MK1, POWER_CONSUMPTION, "5"));
    }};
    public static final List<BuildingAttribute> MINER_MK2_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(7, MINER_MK2, POWER_CONSUMPTION, "12"));
    }};
    public static final List<BuildingAttribute> MINER_MK3_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(8, MINER_MK3, POWER_CONSUMPTION, "30"));
    }};
    public static final List<BuildingAttribute> OIL_EXTRACTOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(9, OIL_EXTRACTOR, POWER_CONSUMPTION, "40"));
    }};
    public static final List<BuildingAttribute> PACKAGER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(10, PACKAGER, POWER_CONSUMPTION, "10"));
    }};
    public static final List<BuildingAttribute> REFINERY_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(11, REFINERY, POWER_CONSUMPTION, "30"));
    }};
    public static final List<BuildingAttribute> SMELTER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(12, SMELTER, POWER_CONSUMPTION, "4"));
    }};
    public static final List<BuildingAttribute> WATER_EXTRACTOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(13, WATER_EXTRACTOR, POWER_CONSUMPTION, "20"));
    }};
    public static final List<BuildingAttribute> PARTICLE_ACCELERATOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(14, PARTICLE_ACCELERATOR, POWER_CONSUMPTION, "1500"));
        add(new BuildingAttribute(15, PARTICLE_ACCELERATOR, VARIABLE_MIN_POWER_CONSUMPTION, "250"));
    }};
    public static final List<BuildingAttribute> RESOURCE_WELL_PRESSURIZER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(16, RESOURCE_WELL_PRESSURIZER, POWER_CONSUMPTION, "150"));
    }};
    public static final List<BuildingAttribute> BIOMASS_BURNER_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(17, BIOMASS_BURNER, POWER_PRODUCTION, "30"));
    }};
    public static final List<BuildingAttribute> COAL_GENERATOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(18, COAL_GENERATOR, POWER_PRODUCTION, "75"));
    }};
    public static final List<BuildingAttribute> FUEL_GENERATOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(19, FUEL_GENERATOR, POWER_PRODUCTION, "150"));
    }};
    public static final List<BuildingAttribute> GEOTHERMAL_GENERATOR_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(20, GEOTHERMAL_GENERATOR, POWER_PRODUCTION, "600"));
        add(new BuildingAttribute(21, GEOTHERMAL_GENERATOR, VARIABLE_MIN_POWER_PRODUCTION, "50"));
    }};
    public static final List<BuildingAttribute> NUCLEAR_POWER_PLANT_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(22, NUCLEAR_POWER_PLANT, POWER_PRODUCTION, "2500"));
    }};
    public static final List<BuildingAttribute> POWER_STORAGE_ATTRIBUTES = new ArrayList<BuildingAttribute>() {{
        add(new BuildingAttribute(23, POWER_STORAGE, POWER_CONSUMPTION, "100"));
        add(new BuildingAttribute(24, POWER_STORAGE, POWER_PRODUCTION, "100"));
    }};
}
