package RecipeGeneratorApp.Controller;

import RecipeGeneratorApp.Entity.Inventory;
import RecipeGeneratorApp.Entity.Recipe;
import RecipeGeneratorApp.Service.IngredientsService;
import RecipeGeneratorApp.Service.InventoryService;
import RecipeGeneratorApp.Service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventory-controller")
public class InventoryController{
    private final RecipeService recipeService;
    private final IngredientsService ingredientsService;
    private final InventoryService inventoryService;

    public InventoryController(RecipeService recipeService, IngredientsService ingredientsService, InventoryService inventoryService){
        this.recipeService = recipeService;
        this.ingredientsService = ingredientsService;
        this.inventoryService = inventoryService;
    }

    @GetMapping(path = "/make-recipe/{id}")
    public String makeRecipe(@PathVariable Long id, Model model){
        Recipe recipe = recipeService.findRecipe(id).get();
        Double[] ingredientPrice = new Double[recipe.getIngredients().size()];
        List<String> evaluation = inventoryService.recipeEvaluation(recipe.getIngredients());
        List<String> resultStatus = inventoryService.inventoryStatus(recipe.getIngredients(), ingredientPrice);
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("resultStatus", resultStatus);
        model.addAttribute("ingredientPrice", ingredientPrice);
        return "make-recipe";
    }

    @GetMapping(path = "/inventory")
    public String getInventory(Model model){
        model.addAttribute("inventory", inventoryService.getAllInventories());
        return "inventory";
    }

    @GetMapping(path = "/new-element")
    public String newElement(Model model){
        Inventory inventory =  new Inventory();
        model.addAttribute("inventory", inventory);
        return "new-element";
    }

    @RequestMapping(value = "/save-element", method = RequestMethod.POST)
    public String saveElement(@ModelAttribute("inventory") Inventory inventory){
        inventoryService.addElement(inventory);
        return "redirect:/inventory-controller/inventory";
    }

    @GetMapping("/edit-element/{id}")
    public String editElement(@PathVariable Long id, Model model){
        model.addAttribute("inventory", inventoryService.findElement(id));
        return "edit-element";
    }

    @RequestMapping(value = "/save-edited-element", method = RequestMethod.POST)
    public String saveEditedElement(@ModelAttribute("inventory") Inventory editedInventory){
        inventoryService.updateElement(editedInventory);
        inventoryService.getAllInventories();
        return "redirect:/inventory-controller/inventory";
    }

    @GetMapping(path = "/delete-element/{id}")
    public String deleteElement(@PathVariable Long id){
        inventoryService.deleteElement(id);
        return "redirect:/inventory-controller/inventory";
    }

}
