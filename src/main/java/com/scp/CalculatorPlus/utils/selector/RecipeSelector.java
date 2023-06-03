package com.scp.CalculatorPlus.utils.selector;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;

@FunctionalInterface
public interface RecipeSelector {

    Recipe selectBestRecipe(Item item);
}
