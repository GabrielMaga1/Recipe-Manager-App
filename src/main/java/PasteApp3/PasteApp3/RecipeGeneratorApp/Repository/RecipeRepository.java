package PasteApp3.PasteApp3.RecipeGeneratorApp.Repository;

import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    @Query("SELECT r FROM Recipe r WHERE EXISTS (SELECT i FROM r.ingredients i WHERE i.name IN :ingredientNames) " +
            "AND NOT EXISTS (SELECT i FROM r.ingredients i WHERE i.name NOT IN :ingredientNames)")
    List<Recipe> findByIngredientNames(@Param("ingredientNames") List<String> ingredientNames);

    Recipe findByName(String name);
}
