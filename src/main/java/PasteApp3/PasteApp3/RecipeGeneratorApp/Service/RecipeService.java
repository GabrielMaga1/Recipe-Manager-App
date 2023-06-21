package PasteApp3.PasteApp3.RecipeGeneratorApp.Service;
import java.util.*;

import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.Ingredients;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.RecipeDTO;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.Recipe;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Repository.IngredientsRepository;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService{

    private final  RecipeRepository recipeRepository;
    private final IngredientsRepository ingredientRepository;
    private final InventoryService inventoryService;

    public RecipeService(RecipeRepository recipeRepository, IngredientsRepository ingredientRepository, InventoryService inventoryService){
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.inventoryService = inventoryService;
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public String ingredientsAndQuantities(List<Ingredients> ingredients){
        String ingredientsAndQuantities = new String("");

        for (Ingredients ingredient : ingredients){
            ingredientsAndQuantities += ingredient.getName() + " - " + String.valueOf(ingredient.getQuantity()) + " " + ingredient.getUnit() + "\n";
        }
        return ingredientsAndQuantities;
    }

    public void changeQuantitiesByServes(int oldServes, int newServes, List<Ingredients> ingredients){
        for(Ingredients ingredient : ingredients){
            ingredient.setQuantity((ingredient.getQuantity() / oldServes) * newServes);
            ingredient.setQuantity(Math.round(ingredient.getQuantity() * 10.0) / 10.0);
        }
    }

    public RecipeDTO convertRecipeToDTO(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setServes(recipe.getServes());
        recipeDTO.setDifficulty(recipe.getDifficulty());
        recipeDTO.setCategory(recipe.getCategory());
        recipeDTO.setText(recipe.getText());
        List<Ingredients> ingredients = recipe.getIngredients();
        List<String> ingredientName = new ArrayList<>();
        List<String> ingredientQuantity = new ArrayList<>();
        List<String> ingredientUnit = new ArrayList<>();

        for(Ingredients ingredient : ingredients ) {
            ingredientName.add(ingredient.getName());
            ingredientQuantity.add(String.valueOf(ingredient.getQuantity()));
            ingredientUnit.add(ingredient.getUnit());
        }
        recipeDTO.setIngredientName(ingredientName);
        recipeDTO.setIngredientQuantity(ingredientQuantity);
        recipeDTO.setIngredientUnit(ingredientUnit);

        return recipeDTO;
    }
    public Recipe convertRecipeDTOToRecipe(RecipeDTO recipeDTO){
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setName(recipeDTO.getName());
        recipe.setServes(recipeDTO.getServes());
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setCategory(recipeDTO.getCategory());
        recipe.setText(recipeDTO.getText());
        List<String> ingredientsName = recipeDTO.getIngredientName();
        List<String> ingredientsQuantity = recipeDTO.getIngredientQuantity();
        List<String> ingredientsUnit = recipeDTO.getIngredientUnit();
        List<Ingredients> ingredients = new ArrayList<>();

        for (int i = 0; i < ingredientsName.size(); ++i){
            Ingredients ingredient = new Ingredients();
            ingredient.setName(ingredientsName.get(i));
            ingredient.setQuantity(Double.valueOf(ingredientsQuantity.get(i)));
            ingredient.setUnit(ingredientsUnit.get(i));
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        return recipe;
    }

    public RecipeDTO reorderLists(RecipeDTO recipeDTO){
        List<String> names = recipeDTO.getIngredientName();
        List<String> quantities = recipeDTO.getIngredientQuantity();
        List<String> units = recipeDTO.getIngredientUnit();
        recipeDTO.setIngredientName(splitAllElements(names));
        recipeDTO.setIngredientQuantity(splitAllElements(quantities));
        recipeDTO.setIngredientUnit(splitAllElements(units));

        return recipeDTO;
    }

    public List<String> splitAllElements(List<String> ingredientsElements){
        List<String> resultList = new ArrayList<>();

        for (String element : ingredientsElements) {
            String[] substrings = element.split(",");
            resultList.addAll(Arrays.asList(substrings));
        }
        return resultList;
    }

    public List<Recipe> searchRecipesByIngredients(List<String> ingredientNames) {
        return recipeRepository.findByIngredientNames(ingredientNames);
    }

    public Recipe findRecipeByName(String recipeName){
        return recipeRepository.findByName(recipeName);
    }

    public void createRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }

    public Optional<Recipe> findRecipe(Long id){
       return recipeRepository.findById(id);
    }
}
