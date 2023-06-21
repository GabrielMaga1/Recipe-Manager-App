package RecipeGeneratorApp.Repository;

import RecipeGeneratorApp.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findFirstByIngredientName(String ingredientName);
}
