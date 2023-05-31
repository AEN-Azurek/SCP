package com.scp.CalculatorPlus.repository;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>  {

    Recipe findByRecipeName(String recipeName);

    List<Recipe> findAllByPrimaryOutput(Item item);

    Recipe findByPrimaryOutputAndDefaultRecipeTrue(Item item);
}
