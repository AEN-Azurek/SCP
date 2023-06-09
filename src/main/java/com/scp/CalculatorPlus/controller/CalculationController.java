package com.scp.CalculatorPlus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scp.CalculatorPlus.model.BuildStep;
import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import com.scp.CalculatorPlus.service.factory.ItemService;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import org.apache.commons.math3.fraction.BigFraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.scp.CalculatorPlus.constants.MapConstants.*;

@RestController
public class CalculationController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeItemService recipeItemService;

    @PostMapping("/item")
    public ResponseEntity<String> getItemByName(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Item item = itemService.findByItemName(json.get(ITEM_NAME));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(item));
    }

    @PostMapping("/recipe")
    public ResponseEntity<String> getRecipeByName(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findByRecipeName(json.get(RECIPE_NAME));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/allRecipes")
    public ResponseEntity<String> getAllRecipesForDesiredItem(@RequestBody Map<String, String> json) throws JsonProcessingException {
        List<Recipe> recipes = recipeService.findAllRecipesForItem(json.get(ITEM_NAME));

        String recipesString = recipes.stream().map(Recipe::getRecipeName).collect(Collectors.joining(", "));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipesString));
    }

    @PostMapping("/defaultRecipe")
    public ResponseEntity<String> getDefaultRecipeForDesiredItem(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findDefaultRecipeForItem(json.get(ITEM_NAME));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/bestRecipe")
    public ResponseEntity<String> getBestRecipeForDesiredItem(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findBestRecipeForItemByNormalizedSinkPoints(json.get(ITEM_NAME));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/recipeItems")
    public ResponseEntity<String> getRecipeItemsForDesiredRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findBestRecipeForItemByNormalizedSinkPoints(json.get(RECIPE_NAME));

        List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

        String recipesString = recipeItems.stream().map((recipeItem) ->
                recipeItem.getQuantity() + " " + recipeItem.getItem().getItemName() + " " + recipeItem.getDirection()
        ).collect(Collectors.joining(", "));

        return ResponseEntity.ok().body(recipesString);
    }

    @PostMapping("/getNormalizedSinkValueForRecipe")
    public ResponseEntity<String> getNormalizedSinkValueForRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findBestRecipeForItemByNormalizedSinkPoints(json.get(RECIPE_NAME));

        BigFraction normalizedSinkValue = recipeService.getNormalizedSinkValueOfRecipe(recipe);

        return ResponseEntity.ok().body(normalizedSinkValue.getNumeratorAsInt() + "/" + normalizedSinkValue.getDenominatorAsInt());
    }

    @PostMapping("/findBestRecipeForItemByNormalizedSinkPoints")
    public ResponseEntity<String> findBestRecipeForItemByNormalizedSinkPoints(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findBestRecipeForItemByNormalizedSinkPoints(json.get(ITEM_NAME));

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/calculateEntireRecipe")
    public ResponseEntity<String> calculateEntireRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findBestRecipeForItemByNormalizedSinkPoints(json.get(ITEM_NAME));
        BigFraction quantity = new BigFraction(Integer.valueOf(json.get(QUANTITY)));

        StringBuilder buildSteps = new StringBuilder();
        List<BuildStep> buildStepsList = recipeService.calculateBuildStepsForEntireRecipe(recipe, quantity);

        buildSteps.append("<p>");
        buildSteps.append("To build ").append(quantity).append(" ");
        buildSteps.append(recipe.getPrimaryOutput().getItemName());
        buildSteps.append(" using the recipe ").append(recipe.getRecipeName());
        buildSteps.append(", use the following set up");
        buildSteps.append("</p>");

        for (BuildStep buildStep : buildStepsList) {
            buildSteps.append("<p>");
            buildSteps.append(buildStep.toString());
            buildSteps.append("</p>");
        }

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(buildSteps.toString()));
    }
}
