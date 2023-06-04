package com.scp.CalculatorPlus.model;

import com.scp.CalculatorPlus.model.buildings.Building;
import org.apache.commons.math3.fraction.BigFraction;

import static com.scp.CalculatorPlus.utils.StringUtils.*;

public class BuildStep {
    String itemName;

    String recipeName;

    BigFraction itemQuantity;

    Building building;

    BigFraction buildingQuantity;

    public BuildStep(Recipe recipe, BigFraction itemQuantity, BigFraction buildingQuantity) {
        this.itemName = recipe.getPrimaryOutput().getItemName();
        this.recipeName = recipe.getRecipeName();
        this.itemQuantity = itemQuantity;
        this.building = recipe.getBuilding();
        this.buildingQuantity = buildingQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public BigFraction getItemQuantity() {
        return itemQuantity;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public Building getBuilding() {
        return building;
    }

    public BigFraction getBuildingQuantity() {
        return buildingQuantity;
    }

    public String toString() {
        String buildingName = building.getName();
        String pluralBuilding = buildingQuantity != BigFraction.ONE
                ? pluralizeWord(buildingName)
                : buildingName;

        return itemQuantity.bigDecimalValue() +
                " " + itemName +
                " in " + bigFractionToString(buildingQuantity) +
                " " + pluralBuilding +
                " using the recipe " + recipeName;
    }

    public void add(BuildStep value) {
        this.itemQuantity = this.itemQuantity.add(value.itemQuantity);
        this.buildingQuantity = this.buildingQuantity.add(value.buildingQuantity);
    }
}
