package com.scp.CalculatorPlus.repository;

import com.scp.CalculatorPlus.model.Recipe;
import com.scp.CalculatorPlus.model.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long> {

    List<RecipeItem> findByRecipe(Recipe recipe);
}
