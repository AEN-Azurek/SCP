package com.scp.CalculatorPlus.service.factory;

import com.scp.CalculatorPlus.model.*;
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
     * WIP function as application is developed and more inputs/outputs are considered <br>
     * Determines the best recipe for an item based on the
     *
     * @param itemName - String of desired output item
     * @return Optimal recipe given inputs
     */
    public Recipe findBestRecipeForItemByNormalizedSinkPoints(String itemName) {
        return findBestRecipeForItemByNormalizedSinkPoints(itemService.findByItemName(itemName));
    }

    /**
     * WIP function as application is developed and more inputs/outputs are considered
     *
     * @param item - Desired output item
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

    public void findBestRecipeForItemByPowerConsumption() {
        // TODO: Best recipe by power consumption -> will need building power consumption
        // TODO: Determine how to code for varying power (ex. Particle Accelerator)
    }

    public void findBestRecipeForItemByBuildingFootprint() {
        // TODO: Best recipe by building footprint -> Will need building width and length
    }

    /**
     * Given the parameters, determines the normalized sink value of a recipe.<br>
     * This can be described as the factor by which the base resources sink values are being multiplied
     * by to reach the output items sink value. The higher, the better. <br>
     * <u>Formula</u>: <i>output_item_sink_value</i> / <i>total_base_resource_sink_value</i>
     *
     * @param recipe - Recipe object to find the normalized sink value for
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param isPrimaryRecipe - Specifies whether this recipe is the primary recipe we are determining the value for
     * @param recipe - Recipe object to find the normalized sink value for
     * @param quantity - Desired number of primary output of the given Recipe
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param recipe - Recipe object to find the normalized sink value for
     * @param quantity - Desired number of primary output of the given Recipe
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param recipe - Recipe object to find the normalized sink value for
     * @param recipeItems - List of items associated with the given Recipe
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param isPrimaryRecipe - Specifies whether this recipe is the primary recipe we are determining the value for
     * @param recipe - Recipe object to find the normalized sink value for
     * @param recipeItems - List of items associated with the given Recipe
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param isPrimaryRecipe - Specifies whether this recipe is the primary recipe we are determining the value for
     * @param recipe - Recipe object to find the normalized sink value for
     * @param recipeItems - List of items associated with the given Recipe
     * @param quantity - Desired number of primary output of the given Recipe
     * @return the factor by which the base input resources total sink points are bing increased by to achieve the output sink points
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
     * @param recipe - Recipe object to find best build steps for
     * @param quantity - Desired number of primary output of the given Recipe
     * @return the steps needed to achieve the desired output
     */
    public BuildSteps getAllBuildStepsForBestRecipeByNormalizedSinkValue(Recipe recipe, BigFraction quantity) {
        BuildSteps buildSteps = new BuildSteps();
        Map<Recipe, BuildStep> buildStepMap = new LinkedHashMap<>();
        Map<Item, BigFraction> baseResourceInputQuantity = new LinkedHashMap<>();
        List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

        RecipeItem primaryRecipeItem = recipeItemService.findPrimaryItem(recipe, recipeItems);

        BigFraction itemsPerMinute = recipe.getItemsPerMinute(primaryRecipeItem.getQuantity());
        BigFraction buildingQuantity = quantity.divide(itemsPerMinute);

        buildStepMap.put(recipe,
                new BuildStep(
                recipe,
                quantity,
                buildingQuantity
        ));

        buildSteps.addToBuildStepMap(buildStepMap);

        for (RecipeItem recipeItem : recipeItems) {
            if (!recipeItemService.isRecipeItemInput(recipeItem)) continue;
            if(!recipeItem.getItem().hasRecipe()) {
                BigFraction itemsRequiredPerMinuteForRecipe = new BigFraction(recipeItem.getQuantity()).multiply(recipe.getCyclesPerMinute());
                baseResourceInputQuantity.put(recipeItem.getItem(), itemsRequiredPerMinuteForRecipe.multiply(buildingQuantity));
                buildSteps.addToBaseResourceInputQuantity(baseResourceInputQuantity);
                continue;
            }
            BigFraction recipeItemsQuantity = new BigFraction(recipeItemService.findPrimaryItem(recipe, recipeItems).getQuantity());
            BigFraction totalRecipeItemsQuantity = quantity.divide(recipeItemsQuantity);
            BuildSteps requisiteBuildSteps = getAllBuildStepsForBestRecipeByNormalizedSinkValue(
                    findBestRecipeForItemByNormalizedSinkPoints(
                            recipeItem.getItem()),
                    totalRecipeItemsQuantity
            );
            buildSteps.addToBuildStepMap(requisiteBuildSteps.getBuildStepList());
            buildSteps.addToBaseResourceInputQuantity(requisiteBuildSteps.getBaseResourceInputQuantity());
        }

        return buildSteps;
    }

}
