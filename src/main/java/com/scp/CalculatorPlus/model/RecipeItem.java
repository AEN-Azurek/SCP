package com.scp.CalculatorPlus.model;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "recipe_items", schema = "dbo")
public class RecipeItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "direction")
    private String direction;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "date_created")
    private java.sql.Timestamp dateCreated;

    public RecipeItem() {
    }

    public RecipeItem(long id, Recipe recipe, Item item, String direction, int quantity) {
        this.id = id;
        this.recipe = recipe;
        this.item = item;
        this.direction = direction;
        this.quantity = quantity;
        this.dateCreated = Timestamp.from(Instant.now());
    }

    public RecipeItem(long id, Recipe recipe, Item item, String direction, int quantity, Timestamp dateCreated) {
        this.id = id;
        this.recipe = recipe;
        this.item = item;
        this.direction = direction;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipeId(Recipe recipe) {
        this.recipe = recipe;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item itemId) {
        this.item = itemId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return recipe.getRecipeName() + ": " + item.getItemName();
    }
}
