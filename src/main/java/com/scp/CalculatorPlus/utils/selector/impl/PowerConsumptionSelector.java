package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.List;

public class PowerConsumptionSelector implements RecipeSelector {

    // TODO: Best recipe by power consumption -> will need building power consumption
    // TODO: Determine how to code for varying power (ex. Particle Accelerator)

    private RecipeService recipeService;

    public PowerConsumptionSelector(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Find the best recipe for an item based on the power usage of all buildings involved. The best recipe
     * would be considered the recipe to take up the least amount of power.
     * Use this if focus is to optimize power usage.
     * <br>
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param item - Desired output item
     * @return Optimal recipe given inputs
     */
    @Override
    public Recipe selectBestRecipe(Item item) {
        List<Recipe> recipes = recipeService.findAllRecipesForItem(item);
        Double smallest = null;
        Recipe bestRecipe = null;

        for (Recipe recipe : recipes) {
            Double normalizedPowerConsumption = recipeService.getNormalizedPowerUsageOfRecipe(recipe);

            if (smallest == null || normalizedPowerConsumption.compareTo(smallest) < 0) {
                smallest = normalizedPowerConsumption;
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
