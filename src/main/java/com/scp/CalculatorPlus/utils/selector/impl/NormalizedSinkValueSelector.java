package com.scp.CalculatorPlus.utils.selector.impl;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import org.apache.commons.math3.fraction.BigFraction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NormalizedSinkValueSelector implements RecipeSelector {

    private RecipeService recipeService;

    private RecipeItemService recipeItemService;

    public NormalizedSinkValueSelector(RecipeService recipeService, RecipeItemService recipeItemService) {
        this.recipeService = recipeService;
        this.recipeItemService = recipeItemService;
    }

    /**
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param item - Desired output item
     * @return Optimal recipe given inputs
     */
    @Override
    public Recipe selectBestRecipe(Item item) {
        List<Recipe> recipes = recipeService.findAllRecipesForItem(item);
        BigFraction largest = BigFraction.ZERO;
        Recipe bestRecipe = null;

        for (Recipe recipe : recipes) {
            List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

            BigFraction normalizedSinkValue = recipeService.getNormalizedSinkValueOfRecipe(recipe, recipeItems, this);

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

    public void setRecipeItemService(RecipeItemService recipeItemService) {
        this.recipeItemService = recipeItemService;
    }
}
