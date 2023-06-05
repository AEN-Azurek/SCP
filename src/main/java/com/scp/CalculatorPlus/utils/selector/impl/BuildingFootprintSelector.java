package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.List;

public class BuildingFootprintSelector implements RecipeSelector {

    // TODO: Best recipe by building footprint -> Will need building width and length

    private RecipeService recipeService;

    public BuildingFootprintSelector(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @Override
    public Recipe selectBestRecipe(Item item, BigFraction quantity) {
        List<Recipe> recipes = recipeService.findAllRecipesForItem(item);
        Double smallest = null;
        Recipe bestRecipe = null;

        for (Recipe recipe : recipes) {
            Double defaultFootprint = recipeService.getNormalizedFootprintOfRecipe(recipe);

            if (smallest == null || defaultFootprint.compareTo(smallest) < 0) {
                smallest = defaultFootprint;
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }
}
