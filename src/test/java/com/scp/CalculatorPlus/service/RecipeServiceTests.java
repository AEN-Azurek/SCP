package com.scp.CalculatorPlus.service;

import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.repository.RecipeItemRepository;
import com.scp.CalculatorPlus.repository.RecipeRepository;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.impl.NormalizedSinkValueSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static com.scp.CalculatorPlus.constants.models.ItemConstants.*;
import static com.scp.CalculatorPlus.constants.models.RecipeConstants.*;
import static com.scp.CalculatorPlus.constants.models.RecipeItemConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeServiceTests {

    @Mock
    private RecipeRepository recipeRepo;

    @Mock
    private RecipeItemService recipeItemService;

    @InjectMocks
    private RecipeService recipeService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void mockFindAllByPrimaryOutput_Valid() {
        when(recipeRepo.findAllByPrimaryOutput(IRON_INGOT)).thenReturn(Arrays.asList(IRON_INGOT_RECIPE, PURE_IRON_INGOT_RECIPE, IRON_ALLOY_INGOT_RECIPE));
        when(recipeRepo.findAllByPrimaryOutput(COPPER_INGOT)).thenReturn(Arrays.asList(COPPER_INGOT_RECIPE, PURE_COPPER_INGOT_RECIPE, COPPER_ALLOY_INGOT_RECIPE));
        when(recipeRepo.findAllByPrimaryOutput(IRON_ROD)).thenReturn(Arrays.asList(IRON_ROD_RECIPE, STEEL_ROD_RECIPE));
        when(recipeRepo.findAllByPrimaryOutput(STEEL_INGOT)).thenReturn(Arrays.asList(STEEL_INGOT_RECIPE, SOLID_STEEL_INGOT_RECIPE));
        when(recipeRepo.findAllByPrimaryOutput(STEEL_BEAM)).thenReturn(Arrays.asList(STEEL_BEAM_RECIPE));
        when(recipeRepo.findAllByPrimaryOutput(SCREW)).thenReturn(Arrays.asList(SCREW_RECIPE, CAST_SCREW_RECIPE, STEEL_SCREW_RECIPE));
    }

    private void mockGetRecipeItems_Valid() {
        when(recipeItemService.getRecipeItems(IRON_INGOT_RECIPE)).thenReturn(IRON_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(PURE_IRON_INGOT_RECIPE)).thenReturn(PURE_IRON_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(IRON_ALLOY_INGOT_RECIPE)).thenReturn(IRON_ALLOY_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(COPPER_INGOT_RECIPE)).thenReturn(COPPER_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(PURE_COPPER_INGOT_RECIPE)).thenReturn(PURE_COPPER_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(COPPER_ALLOY_INGOT_RECIPE)).thenReturn(COPPER_ALLOY_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(IRON_ROD_RECIPE)).thenReturn(IRON_ROD_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(STEEL_ROD_RECIPE)).thenReturn(STEEL_ROD_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(STEEL_BEAM_RECIPE)).thenReturn(STEEL_BEAM_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(STEEL_INGOT_RECIPE)).thenReturn(STEEL_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(SOLID_STEEL_INGOT_RECIPE)).thenReturn(SOLID_STEEL_INGOT_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(SCREW_RECIPE)).thenReturn(SCREW_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(CAST_SCREW_RECIPE)).thenReturn(CAST_SCREW_RECIPE_ITEMS);
        when(recipeItemService.getRecipeItems(STEEL_SCREW_RECIPE)).thenReturn(STEEL_SCREW_RECIPE_ITEMS);
    }

    private void mockFindPrimaryItem_Valid() {
        when(recipeItemService.findPrimaryItem(IRON_INGOT_RECIPE, IRON_INGOT_RECIPE_ITEMS)).thenReturn(IRON_INGOT_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(PURE_IRON_INGOT_RECIPE, PURE_IRON_INGOT_RECIPE_ITEMS)).thenReturn(PURE_IRON_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(IRON_ALLOY_INGOT_RECIPE, IRON_ALLOY_INGOT_RECIPE_ITEMS)).thenReturn(IRON_ALLOY_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(COPPER_INGOT_RECIPE, COPPER_INGOT_RECIPE_ITEMS)).thenReturn(COPPER_INGOT_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(PURE_COPPER_INGOT_RECIPE, PURE_COPPER_INGOT_RECIPE_ITEMS)).thenReturn(PURE_COPPER_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(COPPER_ALLOY_INGOT_RECIPE, COPPER_ALLOY_INGOT_RECIPE_ITEMS)).thenReturn(COPPER_ALLOY_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(STEEL_INGOT_RECIPE, STEEL_INGOT_RECIPE_ITEMS)).thenReturn(STEEL_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(SOLID_STEEL_INGOT_RECIPE, SOLID_STEEL_INGOT_RECIPE_ITEMS)).thenReturn(SOLID_STEEL_INGOT_RECIPE_ITEMS.get(2));
        when(recipeItemService.findPrimaryItem(IRON_ROD_RECIPE, IRON_ROD_RECIPE_ITEMS)).thenReturn(IRON_ROD_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(STEEL_ROD_RECIPE, STEEL_ROD_RECIPE_ITEMS)).thenReturn(STEEL_ROD_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(STEEL_BEAM_RECIPE, STEEL_BEAM_RECIPE_ITEMS)).thenReturn(STEEL_BEAM_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(SCREW_RECIPE, SCREW_RECIPE_ITEMS)).thenReturn(SCREW_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(CAST_SCREW_RECIPE, CAST_SCREW_RECIPE_ITEMS)).thenReturn(CAST_SCREW_RECIPE_ITEMS.get(1));
        when(recipeItemService.findPrimaryItem(STEEL_SCREW_RECIPE, STEEL_SCREW_RECIPE_ITEMS)).thenReturn(STEEL_SCREW_RECIPE_ITEMS.get(1));
    }

    private void mockIsRecipeItemInput_Valid() {
        when(recipeItemService.isRecipeItemInput(IRON_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(PURE_IRON_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(PURE_IRON_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(IRON_ALLOY_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(IRON_ALLOY_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(COPPER_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(PURE_COPPER_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(PURE_COPPER_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(COPPER_ALLOY_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(COPPER_ALLOY_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(STEEL_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(STEEL_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(SOLID_STEEL_INGOT_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(SOLID_STEEL_INGOT_RECIPE_ITEMS.get(1))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(IRON_ROD_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(STEEL_ROD_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(STEEL_BEAM_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(SCREW_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(CAST_SCREW_RECIPE_ITEMS.get(0))).thenReturn(true);
        when(recipeItemService.isRecipeItemInput(STEEL_SCREW_RECIPE_ITEMS.get(0))).thenReturn(true);
    }

    @Test
    void findBestRecipeForItemByNormalizedSinkPoints_() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();

        NormalizedSinkValueSelector selector = new NormalizedSinkValueSelector(recipeService, recipeItemService);

        Recipe bestRecipe = selector.selectBestRecipe(SCREW);

        assertEquals(SCREW_RECIPE, bestRecipe);
    }
}
