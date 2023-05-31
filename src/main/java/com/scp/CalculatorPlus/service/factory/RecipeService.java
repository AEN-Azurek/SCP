package com.scp.CalculatorPlus.service.factory;

import com.scp.CalculatorPlus.model.BuildStep;
import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import com.scp.CalculatorPlus.repository.RecipeRepository;
import org.apache.commons.math3.fraction.BigFraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {

    Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private RecipeRepository recipeRepo;

    @Autowired
    private RecipeItemService recipeItemService;

    public Recipe findById(Long id) {
        return recipeRepo.findById(id).get();
    }

    public Recipe findByRecipeName(String recipeName) {
        return recipeRepo.findByRecipeName(recipeName);
    }

    public List<Recipe> findAllRecipesForItem(String itemName) {
        return findAllRecipesForItem(itemService.findByItemName(itemName));
    }

    public List<Recipe> findAllRecipesForItem(Item item) {
        return recipeRepo.findAllByPrimaryOutput(item);
    }

    public Recipe findDefaultRecipeForItem(String itemName) {
        return findDefaultRecipeForItem(itemService.findByItemName(itemName));
    }

    public Recipe findDefaultRecipeForItem(Item item) {
        return recipeRepo.findByPrimaryOutputAndDefaultRecipeTrue(item);
    }

    /**
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param itemName - String of item name
     * @return Optimal recipe given inputs
     */
    public Recipe findBestRecipeForItemByNormalizedSinkPoints(String itemName) {
        return findBestRecipeForItemByNormalizedSinkPoints(itemService.findByItemName(itemName));
    }

    /**
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param item - Desired output object
     * @return Optimal recipe given inputs
     */
    public Recipe findBestRecipeForItemByNormalizedSinkPoints(Item item) {
        List<Recipe> recipes = findAllRecipesForItem(item);
        BigFraction largest = BigFraction.ZERO;
        Recipe bestRecipe = null;

        for (Recipe recipe : recipes) {
            List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

            BigFraction normalizedSinkValue = getNormalizedSinkValueOfRecipe(recipe, recipeItems);

            if (normalizedSinkValue.compareTo(largest) > 0) {
                largest = normalizedSinkValue;
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param recipe
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(Recipe recipe) {
        return getNormalizedSinkValueOfRecipe(true, recipe, recipeItemService.getRecipeItems(recipe), BigFraction.ONE);
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param isPrimaryRecipe
     * @param recipe
     * @param quantity
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(boolean isPrimaryRecipe, Recipe recipe, BigFraction quantity) {
        return getNormalizedSinkValueOfRecipe(isPrimaryRecipe, recipe, recipeItemService.getRecipeItems(recipe), quantity);
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param recipe
     * @param quantity
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(Recipe recipe, BigFraction quantity) {
        return getNormalizedSinkValueOfRecipe(true, recipe, recipeItemService.getRecipeItems(recipe), quantity);
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param recipe
     * @param recipeItems
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(Recipe recipe, List<RecipeItem> recipeItems) {
        return getNormalizedSinkValueOfRecipe(true, recipe, recipeItems, BigFraction.ONE);
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param isPrimaryRecipe
     * @param recipe
     * @param recipeItems
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(boolean isPrimaryRecipe, Recipe recipe, List<RecipeItem> recipeItems) {
        return getNormalizedSinkValueOfRecipe(isPrimaryRecipe, recipe, recipeItems, BigFraction.ONE);
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param isPrimaryRecipe
     * @param recipe
     * @param recipeItems
     * @param quantity
     * @return
     */
    public BigFraction getNormalizedSinkValueOfRecipe(
            boolean isPrimaryRecipe,
            Recipe recipe,
            List<RecipeItem> recipeItems,
            BigFraction quantity) {

        BigFraction totalBaseSinkValue = BigFraction.ZERO;

        RecipeItem primaryRecipeItem = recipeItemService.findPrimaryItem(recipe, recipeItems);

        for (RecipeItem recipeItem : recipeItems) {
            if (!recipeItemService.isRecipeItemInput(recipeItem)) continue;

            BigFraction recipeItemQuantity = BigFraction.ZERO.add(recipeItem.getQuantity());

            BigFraction itemQuantity = BigFraction.ZERO;
            itemQuantity = itemQuantity.add(recipeItem.getQuantity());
            itemQuantity = itemQuantity.divide(primaryRecipeItem.getQuantity());
            itemQuantity = itemQuantity.multiply(quantity);

            if (!recipeItem.getItem().hasRecipe()) {
                totalBaseSinkValue = totalBaseSinkValue.add(itemQuantity.multiply(recipeItem.getItem().getSinkValue()));
                continue;
            }

            totalBaseSinkValue = totalBaseSinkValue.add(getNormalizedSinkValueOfRecipe(
                    false,
                    findBestRecipeForItemByNormalizedSinkPoints(recipeItem.getItem()),
                    itemQuantity
            ).multiply(recipeItem.getQuantity()));
        }

        if (!isPrimaryRecipe) return totalBaseSinkValue;

        BigFraction totalOutputSinkValue = BigFraction.ZERO;
        totalOutputSinkValue = totalOutputSinkValue.add(primaryRecipeItem.getItem().getSinkValue());
        totalOutputSinkValue = totalOutputSinkValue.divide(totalBaseSinkValue);

        return totalOutputSinkValue;
    }

    /**
     * Builds a list of steps to display to the user, guiding them toward their desired product <br>
     * TODO: Maybe consider adapting the list into a tree that reflects the complexity of the item
     * where base resources are leaf nodes and outputs are parents of their inputs
     *
     * @param recipe
     * @param quantity
     * @return
     */
    public List<BuildStep> calculateBuildStepsForEntireRecipe(Recipe recipe, BigFraction quantity) {
        List<BuildStep> buildSteps = new ArrayList<>();
        List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

        Collections.sort(recipeItems, (a, b) -> a.getItem().getSinkValue() - b.getItem().getSinkValue());

        BigFraction itemsPerMinute = recipe.getItemsPerMinute(recipeItems.get(0).getQuantity());
        BigFraction buildingQuantity = quantity.divide(itemsPerMinute);

        buildSteps.add(
                new BuildStep(
                recipe,
                quantity,
                buildingQuantity
        ));

        for (RecipeItem recipeItem : recipeItems) {
            if (!recipeItemService.isRecipeItemInput(recipeItem)
                || !recipeItem.getItem().hasRecipe()) continue;
            BigFraction recipeItemsQuantity = new BigFraction(recipeItemService.findPrimaryItem(recipe, recipeItems).getQuantity());
            BigFraction totalRecipeItemsQuantity = itemsPerMinute.divide(recipeItemsQuantity);
            buildSteps.addAll(
                calculateBuildStepsForEntireRecipe(
                    findBestRecipeForItemByNormalizedSinkPoints(
                        recipeItem.getItem()),
                        totalRecipeItemsQuantity
                )
            );
        }

        return buildSteps;
    }
}
