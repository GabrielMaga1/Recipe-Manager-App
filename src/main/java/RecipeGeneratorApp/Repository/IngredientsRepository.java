package RecipeGeneratorApp.Repository;

import RecipeGeneratorApp.Entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    Ingredients findFirstByName(String name);
}
