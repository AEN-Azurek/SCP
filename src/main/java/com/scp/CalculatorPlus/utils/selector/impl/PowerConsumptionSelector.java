package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;

public class PowerConsumptionSelector implements RecipeSelector {

    // TODO: Best recipe by power consumption -> will need building power consumption
    // TODO: Determine how to code for varying power (ex. Particle Accelerator)

    private RecipeService recipeService;

    private RecipeItemService recipeItemService;

    public PowerConsumptionSelector(RecipeService recipeService, RecipeItemService recipeItemService) {
        this.recipeService = recipeService;
        this.recipeItemService = recipeItemService;
    }

    @Override
    public Recipe selectBestRecipe(Item item) {
        return null;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public void setRecipeItemService(RecipeItemService recipeItemService) {
        this.recipeItemService = recipeItemService;
    }
}
