package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;

public class BuildingFootprintSelector implements RecipeSelector {

    // TODO: Best recipe by building footprint -> Will need building width and length

    private RecipeService recipeService;

    private RecipeItemService recipeItemService;

    public BuildingFootprintSelector(RecipeService recipeService, RecipeItemService recipeItemService) {
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
