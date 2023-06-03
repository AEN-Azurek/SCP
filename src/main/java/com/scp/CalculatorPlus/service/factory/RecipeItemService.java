package com.scp.CalculatorPlus.service.factory;

import com.scp.CalculatorPlus.constants.ItemDirection;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import com.scp.CalculatorPlus.repository.RecipeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeItemService {

    @Autowired
    private RecipeItemRepository recipeItemRepo;

    public List<RecipeItem> getRecipeItems(Recipe recipe) {
        return recipeItemRepo.findByRecipe(recipe);
    }

    public boolean isRecipeItemInput(RecipeItem recipeItem) {
        return recipeItem.getDirection().equals(ItemDirection.IN.getDirection());
    }

    public RecipeItem findPrimaryItem(Recipe recipe, List<RecipeItem> recipeItems) {
        for (RecipeItem recipeItem : recipeItems) {
            if (isRecipeItemInput(recipeItem)) continue;
            if (recipeItem.getItem().getId() == recipe.getPrimaryOutput().getId()) {
                return recipeItem;
            }
        }
        return null;
    }
}
