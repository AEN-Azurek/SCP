package com.scp.CalculatorPlus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scp.CalculatorPlus.model.*;
import com.scp.CalculatorPlus.service.factory.ItemService;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import com.scp.CalculatorPlus.utils.selector.impl.NormalizedSinkValueSelector;
import org.apache.commons.math3.fraction.BigFraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.scp.CalculatorPlus.constants.HtmlConstants.*;
import static com.scp.CalculatorPlus.constants.MapConstants.*;
import static com.scp.CalculatorPlus.utils.StringUtils.bigFractionToString;

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
        RecipeSelector selector = new NormalizedSinkValueSelector(recipeService, recipeItemService);
        Item item = itemService.findByItemName(json.get(ITEM_NAME));
        Recipe recipe = selector.selectBestRecipe(item);

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/recipeItems")
    public ResponseEntity<String> getRecipeItemsForDesiredRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        Recipe recipe = recipeService.findByRecipeName(json.get(RECIPE_NAME));

        List<RecipeItem> recipeItems = recipeItemService.getRecipeItems(recipe);

        String recipesString = recipeItems.stream().map((recipeItem) ->
                recipeItem.getQuantity() + " " + recipeItem.getItem().getItemName() + " " + recipeItem.getDirection()
        ).collect(Collectors.joining(", "));

        return ResponseEntity.ok().body(recipesString);
    }

    @PostMapping("/getNormalizedSinkValueForRecipe")
    public ResponseEntity<String> getNormalizedSinkValueForRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        RecipeSelector selector = new NormalizedSinkValueSelector(recipeService, recipeItemService);
        Item item = itemService.findByItemName(json.get(ITEM_NAME));
        Recipe recipe = selector.selectBestRecipe(item);

        BigFraction normalizedSinkValue = recipeService.getNormalizedSinkValueOfRecipe(recipe, selector);

        return ResponseEntity.ok().body(normalizedSinkValue.getNumeratorAsInt() + "/" + normalizedSinkValue.getDenominatorAsInt());
    }

    @PostMapping("/findBestRecipeForItemByNormalizedSinkPoints")
    public ResponseEntity<String> findBestRecipeForItemByNormalizedSinkPoints(@RequestBody Map<String, String> json) throws JsonProcessingException {
        RecipeSelector selector = new NormalizedSinkValueSelector(recipeService, recipeItemService);
        Item item = itemService.findByItemName(json.get(ITEM_NAME));
        Recipe recipe = selector.selectBestRecipe(item);

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(recipe));
    }

    @PostMapping("/calculateEntireRecipe")
    public ResponseEntity<String> calculateEntireRecipe(@RequestBody Map<String, String> json) throws JsonProcessingException {
        RecipeSelector selector = new NormalizedSinkValueSelector(recipeService, recipeItemService);
        Recipe recipe = selector.selectBestRecipe(itemService.findByItemName(json.get(ITEM_NAME)));
        BigFraction quantity = new BigFraction(Integer.parseInt(json.get(QUANTITY)));
        StringBuilder buildStepString = new StringBuilder();

        BuildSteps buildSteps = recipeService.getAllBuildStepsForBestRecipe(recipe, quantity, selector);

        buildStepString.append(PARAGRAPH_OPEN);
        buildStepString.append("To build ").append(quantity).append(" ");
        buildStepString.append(recipe.getPrimaryOutput().getItemName());
        buildStepString.append(" using the recipe ").append(recipe.getRecipeName());
        buildStepString.append(", use the following set up");
        buildStepString.append(PARAGRAPH_CLOSE);

        for (BuildStep buildStep : buildSteps.getBuildStepList().values()) {
            buildStepString.append(PARAGRAPH_OPEN);
            buildStepString.append(buildStep.toString());
            buildStepString.append(PARAGRAPH_CLOSE);
        }

        buildStepString.append(PARAGRAPH_OPEN);
        buildStepString.append("With the following item quantities as inputs:");
        buildStepString.append(PARAGRAPH_CLOSE);

        buildStepString.append(UNORDERED_LIST_OPEN);
        for (Map.Entry<Item, BigFraction> entry : buildSteps.getBaseResourceInputQuantity().entrySet()) {
            buildStepString.append(LIST_ITEM_OPEN);
            buildStepString.append(baseResourceToString(entry));
            buildStepString.append(LIST_ITEM_CLOSE);
        }
        buildStepString.append(UNORDERED_LIST_CLOSE);

        return ResponseEntity.ok().body((new ObjectMapper()).writeValueAsString(buildStepString.toString()));
    }

    private String baseResourceToString(Map.Entry<Item, BigFraction> entry) {
        Item item = entry.getKey();
        String numberOfItems = bigFractionToString(entry.getValue());

        return numberOfItems + " " + item.getItemName() + " per minute";
    }
}
