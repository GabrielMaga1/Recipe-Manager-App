package PasteApp3.PasteApp3.RecipeGeneratorApp.Controller;

import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.Ingredients;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.Recipe;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Entity.RecipeDTO;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Service.IngredientsService;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Service.InventoryService;
import PasteApp3.PasteApp3.RecipeGeneratorApp.Service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RecipeController{

    private final RecipeService recipeService;
    private final IngredientsService ingredientsService;
    private final InventoryService inventoryService;

    public RecipeController(RecipeService recipeService, IngredientsService ingredientsService, InventoryService inventoryService){
        this.recipeService = recipeService;
        this.ingredientsService = ingredientsService;
        this.inventoryService = inventoryService;
    }

    @GetMapping(path = "/recipes-list")
    public String getRecipes(Model model){
        model.addAttribute("recipe", recipeService.getAllRecipes());
        return "recipes-list";
    }
    @GetMapping(path = "/find-recipe")
    public String searchRecipe(@RequestParam("searchByName") String recipeName, Model model){
        Recipe recipe = new Recipe();
        recipe = recipeService.findRecipeByName(recipeName);
        model.addAttribute("recipe", recipe);
        return "find-recipe-by-name.html";
    }
    @GetMapping("/find-recipes")
    public String searchRecipesByIngredients(@RequestParam("ingredients") List<String> ingredients, Model model){
        model.addAttribute("recipe", recipeService.searchRecipesByIngredients(ingredients));
        return "search-by-ingredients";
    }

    @GetMapping(path = "/recipes-list/new-recipe")
    public String newRecipe(Model model){
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "new-recipe";
    }

    @RequestMapping(value = "/save-recipe", method = RequestMethod.POST)
    public String saveNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam("ingredientsString") String ingredientsStrings){
        List<Ingredients> ingredients = new ArrayList<>();
        String[] ingredientsList = ingredientsStrings.split(",");

        for (int i = 0; i < ingredientsList.length; i += 3){
                Ingredients ingredient = new Ingredients();
                ingredient.setName(ingredientsList[i]);
                ingredient.setQuantity(Double.valueOf(ingredientsList[i+1]));
                ingredient.setUnit(ingredientsList[i+2]);
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        recipe.setIngredientsAndQuantities(recipeService.ingredientsAndQuantities(ingredients));
        inventoryService.addIngredientsToInventory(ingredients);
        recipeService.createRecipe(recipe);
        return "redirect:/recipes-list";
    }

    @RequestMapping(value = "/recipe-page/newServes/{id}", method = RequestMethod.GET)
    public String changeServes(@PathVariable Long id, @RequestParam("serves") int newServes){
        Recipe recipe = recipeService.findRecipe(id).orElseThrow();
        List<Ingredients> ingredients = recipe.getIngredients();
        int oldServes = recipe.getServes();
        recipeService.changeQuantitiesByServes(oldServes, newServes, ingredients);
        recipe.setIngredientsAndQuantities(recipeService.ingredientsAndQuantities(ingredients));
        recipe.setServes(newServes);
        recipeService.createRecipe(recipe);
        return "redirect:/recipe-page/{id}";
    }

    @GetMapping("/recipe-page/{id}")
    public String recipePage(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findRecipe(id).orElseThrow());
        return "recipe-page";
    }

    @GetMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes-list";
    }

    @GetMapping(path = "/edit-post/{id}")
    public String editRecipe(@PathVariable Long id, Model model){
        Recipe recipe = recipeService.findRecipe(id).get();
        RecipeDTO recipeDTO = recipeService.convertRecipeToDTO(recipe);
        model.addAttribute("recipeDTO", recipeDTO);
        return "edit-post";
    }

    @RequestMapping(value = "/save-updated-recipe", method = RequestMethod.POST)
    public String saveUpdatedRecipe(@ModelAttribute("recipeDTO") RecipeDTO recipeDTO){
        recipeService.reorderLists(recipeDTO);
        Recipe recipe = recipeService.convertRecipeDTOToRecipe(recipeDTO);
        recipe.setIngredientsAndQuantities(recipeService.ingredientsAndQuantities(recipe.getIngredients()));
        inventoryService.addIngredientsToInventory(recipe.getIngredients());
        recipeService.createRecipe(recipe);
        return "redirect:/recipes-list";
    }

}
