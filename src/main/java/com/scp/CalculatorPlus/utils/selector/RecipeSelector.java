package com.scp.CalculatorPlus.utils.selector;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import org.apache.commons.math3.fraction.BigFraction;

@FunctionalInterface
public interface RecipeSelector {

    /**
     * Generic method used to select the best recipe based on a set of criteria.
     *
     * @param item
     * @return
     */
    Recipe selectBestRecipe(Item item, BigFraction quantity);
}
