package com.scp.CalculatorPlus.constants.models;

import com.scp.CalculatorPlus.model.RecipeItem;

import java.util.ArrayList;
import java.util.List;

import static com.scp.CalculatorPlus.constants.models.ItemConstants.*;
import static com.scp.CalculatorPlus.constants.models.RecipeConstants.*;

public class RecipeItemConstants {

    public static final List<RecipeItem> IRON_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(1, IRON_INGOT_RECIPE, IRON_ORE, "in", 1));
        add(new RecipeItem(2, IRON_INGOT_RECIPE, IRON_INGOT, "out", 1));
    }};

    public static final List<RecipeItem> PURE_IRON_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(3, PURE_IRON_INGOT_RECIPE, WATER, "in", 4));
        add(new RecipeItem(4, PURE_IRON_INGOT_RECIPE, IRON_ORE, "in", 7));
        add(new RecipeItem(5, PURE_IRON_INGOT_RECIPE, IRON_INGOT, "out", 13));
    }};

    public static final List<RecipeItem> IRON_ALLOY_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(6, IRON_ALLOY_INGOT_RECIPE,  IRON_ORE, "in", 2));
        add(new RecipeItem(7, IRON_ALLOY_INGOT_RECIPE,  COPPER_ORE, "in", 2));
        add(new RecipeItem(8, IRON_ALLOY_INGOT_RECIPE,  IRON_INGOT, "out", 5));
    }};

    public static final List<RecipeItem> COPPER_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(9, COPPER_INGOT_RECIPE, COPPER_ORE, "in", 1));
        add(new RecipeItem(10, COPPER_INGOT_RECIPE, COPPER_INGOT, "out", 1));
    }};

    public static final List<RecipeItem> PURE_COPPER_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(11, PURE_COPPER_INGOT_RECIPE, COPPER_ORE, "in", 6));
        add(new RecipeItem(12, PURE_COPPER_INGOT_RECIPE, WATER, "in", 4));
        add(new RecipeItem(13, PURE_COPPER_INGOT_RECIPE, COPPER_INGOT, "out", 15));
    }};

    public static final List<RecipeItem> COPPER_ALLOY_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(14, COPPER_ALLOY_INGOT_RECIPE, COPPER_ORE, "in", 10));
        add(new RecipeItem(15, COPPER_ALLOY_INGOT_RECIPE, IRON_ORE, "in", 5));
        add(new RecipeItem(16, COPPER_ALLOY_INGOT_RECIPE, COPPER_INGOT, "out", 20));
    }};

    public static final List<RecipeItem> STEEL_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(17, STEEL_INGOT_RECIPE, IRON_ORE, "in", 3));
        add(new RecipeItem(18, STEEL_INGOT_RECIPE, COAL, "in", 3));
        add(new RecipeItem(19, STEEL_INGOT_RECIPE, STEEL_INGOT, "out", 3));
    }};

    public static final List<RecipeItem> SOLID_STEEL_INGOT_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(20, SOLID_STEEL_INGOT_RECIPE, IRON_INGOT, "in", 2));
        add(new RecipeItem(21, SOLID_STEEL_INGOT_RECIPE, COAL, "in", 2));
        add(new RecipeItem(22, SOLID_STEEL_INGOT_RECIPE, STEEL_INGOT, "out", 3));
    }};

    public static final List<RecipeItem> IRON_ROD_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(23, IRON_ROD_RECIPE, IRON_INGOT, "in", 1));
        add(new RecipeItem(24, IRON_ROD_RECIPE, IRON_ROD, "out", 1));
    }};

    public static final List<RecipeItem> STEEL_ROD_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(25, STEEL_ROD_RECIPE, STEEL_INGOT, "in", 1));
        add(new RecipeItem(26, STEEL_ROD_RECIPE, IRON_ROD, "out", 4));
    }};

    public static final List<RecipeItem> STEEL_BEAM_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(27, STEEL_BEAM_RECIPE, STEEL_INGOT, "in", 4));
        add(new RecipeItem(28, STEEL_BEAM_RECIPE, STEEL_BEAM, "out", 1));
    }};

    public static final List<RecipeItem> SCREW_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(29, SCREW_RECIPE, IRON_ROD, "in", 1));
        add(new RecipeItem(30, SCREW_RECIPE, SCREW, "out", 4));
    }};

    public static final List<RecipeItem> CAST_SCREW_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(31, CAST_SCREW_RECIPE, IRON_INGOT, "in", 5));
        add(new RecipeItem(32, CAST_SCREW_RECIPE, SCREW, "out", 20));
    }};

    public static final List<RecipeItem> STEEL_SCREW_RECIPE_ITEMS = new ArrayList<RecipeItem>() {{
        add(new RecipeItem(33, STEEL_SCREW_RECIPE, STEEL_BEAM, "in", 1));
        add(new RecipeItem(34, STEEL_SCREW_RECIPE, SCREW, "out", 52));
    }};

}
