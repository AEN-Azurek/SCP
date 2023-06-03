package com.scp.CalculatorPlus.constants.models;

import com.scp.CalculatorPlus.model.Recipe;

import static com.scp.CalculatorPlus.constants.models.ItemConstants.*;
import static com.scp.CalculatorPlus.constants.models.buildings.BuildingConstants.*;

public class RecipeConstants {

    public static final Recipe IRON_INGOT_RECIPE = new Recipe(1, "Iron Ingot", SMELTER, IRON_INGOT, 2, true);
    public static final Recipe PURE_IRON_INGOT_RECIPE = new Recipe(2, "Pure Iron Ingot", REFINERY, IRON_INGOT, 12, true);
    public static final Recipe IRON_ALLOY_INGOT_RECIPE = new Recipe(3, "Iron Alloy Ingot", FOUNDRY, IRON_INGOT, 6, true);
    public static final Recipe COPPER_INGOT_RECIPE = new Recipe(4, "Copper Ingot", SMELTER, COPPER_INGOT, 2, true);
    public static final Recipe PURE_COPPER_INGOT_RECIPE = new Recipe(5, "Pure Copper Ingot", REFINERY, COPPER_INGOT, 24, true);
    public static final Recipe COPPER_ALLOY_INGOT_RECIPE = new Recipe(6, "Copper Alloy Ingot", FOUNDRY, COPPER_INGOT, 12, true);
    public static final Recipe STEEL_INGOT_RECIPE = new Recipe(7, "Steel Ingot", FOUNDRY, STEEL_INGOT, 4, true);
    public static final Recipe SOLID_STEEL_INGOT_RECIPE = new Recipe(8, "Solid Steel Ingot", FOUNDRY, STEEL_INGOT, 3, true);
    public static final Recipe IRON_ROD_RECIPE = new Recipe(9, "Iron Rod", CONSTRUCTOR, IRON_ROD, 4, true);
    public static final Recipe STEEL_ROD_RECIPE = new Recipe(10, "Steel Rod", CONSTRUCTOR, IRON_ROD, 5, true);
    public static final Recipe STEEL_BEAM_RECIPE = new Recipe(11, "Steel Beam", CONSTRUCTOR, STEEL_BEAM, 4, true);
    public static final Recipe SCREW_RECIPE = new Recipe(12, "Screw", CONSTRUCTOR, SCREW, 6, true);
    public static final Recipe CAST_SCREW_RECIPE = new Recipe(13, "Cast Screw", CONSTRUCTOR, SCREW, 24, true);
    public static final Recipe STEEL_SCREW_RECIPE = new Recipe(14, "Steel Screw", CONSTRUCTOR, SCREW, 12, true);
}
