package com.scp.CalculatorPlus.model;

import org.apache.commons.math3.fraction.BigFraction;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "recipe", schema = "dbo")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "building_name")
    private String buildingName;

    @ManyToOne
    @JoinColumn(name = "primary_output")
    private Item primaryOutput;

    @Column(name = "crafting_time")
    private double craftingTime;

    @Column(name = "default_recipe")
    private boolean defaultRecipe;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;


    public Recipe() {
    }

    public Recipe(long id, String recipeName, String buildingName, Item primaryOutput, double craftingTime, boolean defaultRecipe, Timestamp dateCreated) {
        this.id = id;
        this.recipeName = recipeName;
        this.buildingName = buildingName;
        this.primaryOutput = primaryOutput;
        this.craftingTime = craftingTime;
        this.defaultRecipe = defaultRecipe;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Item getPrimaryOutput() {
        return primaryOutput;
    }

    public void setPrimaryOutput(Item primaryOutput) {
        this.primaryOutput = primaryOutput;
    }

    public double getCraftingTime() {
        return craftingTime;
    }

    public void setCraftingTime(double craftingTime) {
        this.craftingTime = craftingTime;
    }

    public boolean isDefaultRecipe() {
        return defaultRecipe;
    }

    public void setDefaultRecipe(boolean defaultRecipe) {
        this.defaultRecipe = defaultRecipe;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigFraction getCyclesPerMinute() {
        return new BigFraction(60).divide(new BigFraction(craftingTime));
    }

    public BigFraction getItemsPerMinute(int quantity) {
        return getCyclesPerMinute().multiply(new BigFraction(quantity));
    }

    @Override
    public String toString() {
        return recipeName;
    }
}
