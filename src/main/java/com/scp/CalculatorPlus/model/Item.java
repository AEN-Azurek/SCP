package com.scp.CalculatorPlus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "item", schema = "dbo")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "has_recipe")
    private boolean hasRecipe;

    @Column(name = "sink_value")
    private Integer sinkValue;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    public Item() {
    }

    public Item(long id, String itemName, boolean hasRecipe, int sinkValue) {
        this.id = id;
        this.itemName = itemName;
        this.hasRecipe = hasRecipe;
        this.sinkValue = sinkValue;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public Item(long id, String itemName, boolean hasRecipe, int sinkValue, Timestamp dateCreated) {
        this.id = id;
        this.itemName = itemName;
        this.hasRecipe = hasRecipe;
        this.sinkValue = sinkValue;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean hasRecipe() {
        return hasRecipe;
    }

    public void setHasRecipe(boolean hasRecipe) {
        this.hasRecipe = hasRecipe;
    }

    public int getSinkValue() {
        return sinkValue;
    }

    public void setSinkValue(int sinkValue) {
        this.sinkValue = sinkValue;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
