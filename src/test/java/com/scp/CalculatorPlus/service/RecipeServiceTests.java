package com.scp.CalculatorPlus.service;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.buildings.BuildingAttribute;
import com.scp.CalculatorPlus.repository.RecipeRepository;
import com.scp.CalculatorPlus.service.factory.AttributeService;
import com.scp.CalculatorPlus.service.factory.RecipeItemService;
import com.scp.CalculatorPlus.service.factory.RecipeService;
import com.scp.CalculatorPlus.utils.selector.RecipeSelector;
import com.scp.CalculatorPlus.utils.selector.impl.BuildingFootprintSelector;
import com.scp.CalculatorPlus.utils.selector.impl.NormalizedSinkValueSelector;
import com.scp.CalculatorPlus.utils.selector.impl.PowerConsumptionSelector;
import org.apache.commons.math3.fraction.BigFraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static com.scp.CalculatorPlus.constants.models.ItemConstants.*;
import static com.scp.CalculatorPlus.constants.models.RecipeConstants.*;
import static com.scp.CalculatorPlus.constants.models.RecipeItemConstants.*;
import static com.scp.CalculatorPlus.constants.models.buildings.AttributeConstants.*;
import static com.scp.CalculatorPlus.constants.models.buildings.BuildingAttributeConstants.*;
import static com.scp.CalculatorPlus.constants.models.buildings.BuildingConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeServiceTests {

    @Mock
    private AttributeService attributeService;

    @Mock
    private RecipeRepository recipeRepo;

    @Mock
    private RecipeItemService recipeItemService;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeAll
    static void init() {
        ASSEMBLER.addAllAttributes(ASSEMBLER_ATTRIBUTES);
        BLENDER.addAllAttributes(BLENDER_ATTRIBUTES);
        CONSTRUCTOR.addAllAttributes(CONSTRUCTOR_ATTRIBUTES);
        FOUNDRY.addAllAttributes(FOUNDRY_ATTRIBUTES);
        MANUFACTURER.addAllAttributes(MANUFACTURER_ATTRIBUTES);
        MINER_MK1.addAllAttributes(MINER_MK1_ATTRIBUTES);
        MINER_MK2.addAllAttributes(MINER_MK2_ATTRIBUTES);
        MINER_MK3.addAllAttributes(MINER_MK3_ATTRIBUTES);
        OIL_EXTRACTOR.addAllAttributes(OIL_EXTRACTOR_ATTRIBUTES);
        PACKAGER.addAllAttributes(PACKAGER_ATTRIBUTES);
        REFINERY.addAllAttributes(REFINERY_ATTRIBUTES);
        SMELTER.addAllAttributes(SMELTER_ATTRIBUTES);
        WATER_EXTRACTOR.addAllAttributes(WATER_EXTRACTOR_ATTRIBUTES);
        PARTICLE_ACCELERATOR.addAllAttributes(PARTICLE_ACCELERATOR_ATTRIBUTES);
        RESOURCE_WELL_PRESSURIZER.addAllAttributes(RESOURCE_WELL_PRESSURIZER_ATTRIBUTES);
        BIOMASS_BURNER.addAllAttributes(BIOMASS_BURNER_ATTRIBUTES);
        COAL_GENERATOR.addAllAttributes(COAL_GENERATOR_ATTRIBUTES);
        FUEL_GENERATOR.addAllAttributes(FUEL_GENERATOR_ATTRIBUTES);
        GEOTHERMAL_GENERATOR.addAllAttributes(GEOTHERMAL_GENERATOR_ATTRIBUTES);
        NUCLEAR_POWER_PLANT.addAllAttributes(NUCLEAR_POWER_PLANT_ATTRIBUTES);
        POWER_STORAGE.addAllAttributes(POWER_STORAGE_ATTRIBUTES);
    }

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

    private void mockFindAttributeByName() {
        when(attributeService.findByAttributeName(POWER_CONSUMPTION_STRING)).thenReturn(POWER_CONSUMPTION);
        when(attributeService.findByAttributeName(POWER_PRODUCTION_STRING)).thenReturn(POWER_PRODUCTION);
        when(attributeService.findByAttributeName(VARIABLE_MIN_POWER_CONSUMPTION_STRING)).thenReturn(VARIABLE_MIN_POWER_CONSUMPTION);
        when(attributeService.findByAttributeName(VARIABLE_MIN_POWER_PRODUCTION_STRING)).thenReturn(VARIABLE_MIN_POWER_PRODUCTION);
    }

    @Test
    void findBestRecipeForItemByNormalizedSinkPoints_ShouldBeNormalScrewRecipe() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();

        RecipeSelector selector = new NormalizedSinkValueSelector(recipeService);

        assertBestRecipe(selector, SCREW, SCREW_RECIPE, null);
    }

    @Test
    void findBestRecipeForItemByPowerUsage_ShouldBeSteelScrewRecipe() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();
        mockFindAttributeByName();

        RecipeSelector selector = new PowerConsumptionSelector(recipeService);

        assertBestRecipe(selector, SCREW, STEEL_SCREW_RECIPE, new BigFraction(260));
    }

    @Test
    void findBestRecipeForItemByPowerUsage_ShouldBe6_05() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();
        mockFindAttributeByName();

        BigDecimal powerUsage = recipeService.getPowerUsageOfRecipe(STEEL_BEAM_RECIPE, new BigFraction(5));

        assertEquals(new BigDecimal("6.05").setScale(2, RoundingMode.HALF_UP), powerUsage);
    }

    @Test
    void findBestRecipeForItemByPowerUsage_ShouldBe10_05() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();
        mockFindAttributeByName();

        BigDecimal powerUsage = recipeService.getPowerUsageOfRecipe(STEEL_SCREW_RECIPE, new BigFraction(260));

        assertEquals(new BigDecimal("10.05").setScale(2, RoundingMode.HALF_UP), powerUsage);
    }

    @Test
    void findBestRecipeForItemByFootprint_ShouldBe() {
        mockFindAllByPrimaryOutput_Valid();
        mockGetRecipeItems_Valid();
        mockFindPrimaryItem_Valid();
        mockIsRecipeItemInput_Valid();
        mockFindAttributeByName();

        RecipeSelector selector = new BuildingFootprintSelector(recipeService);

        assertBestRecipe(selector, SCREW, STEEL_SCREW_RECIPE, null);
    }

    private void assertBestRecipe(RecipeSelector selector, Item item, Recipe recipe, BigFraction quantity) {
        Recipe bestRecipe = selector.selectBestRecipe(item, quantity);
        assertEquals(recipe, bestRecipe);
    }
}
