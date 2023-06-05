package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import org.apache.commons.math3.fraction.BigFraction;

import java.util.List;

public class NormalizedSinkValueSelector implements RecipeSelector {

    private RecipeService recipeService;

    public NormalizedSinkValueSelector(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Find the best recipe for an item based on the normalized factor of base resource
     * sink points to resulting product sink points. The larger the factor, the more sink points generated
     * from the same input resource. Use this if focus is to optimize input resource quantities.
     * <br>
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param item - Desired output item
     * @return Optimal recipe given inputs
     */
    @Override
    public Recipe selectBestRecipe(Item item, BigFraction quantity) {
        List<Recipe> recipes = recipeService.findAllRecipesForItem(item);
        BigFraction largest = BigFraction.ZERO;
        Recipe bestRecipe = null;

        for (Recipe recipe : recipes) {
            BigFraction normalizedSinkValue = recipeService.getNormalizedSinkValueOfRecipe(recipe);

            if (normalizedSinkValue.compareTo(largest) > 0) {
                largest = normalizedSinkValue;
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
