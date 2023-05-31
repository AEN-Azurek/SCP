package com.scp.CalculatorPlus.model;

import org.apache.commons.math3.fraction.BigFraction;

public class BuildStep {
    String itemName;

    String recipeName;

    BigFraction itemQuantity;

    String building;

    BigFraction buildingQuantity;

    public BuildStep(Recipe recipe, BigFraction itemQuantity, BigFraction buildingQuantity) {
        this.itemName = recipe.getPrimaryOutput().getItemName();
        this.recipeName = recipe.getRecipeName();
        this.itemQuantity = itemQuantity;
        this.building = recipe.getBuildingName();
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

    public String getBuilding() {
        return building;
    }

    public BigFraction getBuildingQuantity() {
        return buildingQuantity;
    }

    public String toString() {
        String plural = buildingQuantity != BigFraction.ONE ? "s" : "";
        String pluralBuilding = building.substring(building.length() - 1).equals("y") && plural == "s"
                ? building.substring(0, building.length() - 1).concat("ie")
                : building;

        String displayItemQuantity = "";
        String displayBuildingQuantity = "";

        return itemQuantity +
                " " + itemName +
                " in " + buildingQuantity +
                " " + pluralBuilding + plural +
                " using the recipe " + recipeName;
    }
}
