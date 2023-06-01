package com.scp.CalculatorPlus.model;

import com.scp.CalculatorPlus.model.buildings.Building;
import org.apache.commons.math3.fraction.BigFraction;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "recipe", schema = "dbo")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "primary_output")
    private Item primaryOutput;

    @Column(name = "crafting_time")
    private Double craftingTime;

    @Column(name = "default_recipe")
    private boolean defaultRecipe;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    public Recipe() {
    }

    public Recipe(long id, String recipeName, Building building, Item primaryOutput, double craftingTime,
                  boolean defaultRecipe) {
        this.id = id;
        this.recipeName = recipeName;
        this.building = building;
        this.primaryOutput = primaryOutput;
        this.craftingTime = craftingTime;
        this.defaultRecipe = defaultRecipe;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public Recipe(long id, String recipeName, Building building, Item primaryOutput, double craftingTime,
                  boolean defaultRecipe, Timestamp dateCreated) {
        this.id = id;
        this.recipeName = recipeName;
        this.building = building;
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

    public Building getBuildingName() {
        return building;
    }

    public void setBuildingName(Building building) {
        this.building = building;
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
