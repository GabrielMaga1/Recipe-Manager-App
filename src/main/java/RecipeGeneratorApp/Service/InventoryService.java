package RecipeGeneratorApp.Service;

import RecipeGeneratorApp.Entity.Ingredients;
import RecipeGeneratorApp.Entity.Inventory;
import RecipeGeneratorApp.Repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addIngredientsToInventory(List<Ingredients> ingredients) {
        for (Ingredients ingredient : ingredients) {
            Inventory existingInventory = inventoryRepository.findFirstByIngredientName(ingredient.getName());

            if (existingInventory == null) {
                Inventory newInventory = new Inventory();
                newInventory.setIngredientName(ingredient.getName());
                newInventory.setUnit(ingredient.getUnit());
                newInventory.setPrice(0.0);
                inventoryRepository.save(newInventory);
            }
        }
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    public void addElement(Inventory inventory){
        Inventory existingInventory = inventoryRepository.findFirstByIngredientName(inventory.getIngredientName());
        if (existingInventory == null) {
            inventoryRepository.save(inventory);
        }
    }

    public List<String> recipeEvaluation(List<Ingredients> ingredients){
        List<String> evaluation = new ArrayList<>();
        for(int i = 0; i < ingredients.size(); ++i){
            Inventory inventory = inventoryRepository.findFirstByIngredientName(ingredients.get(i).getName());
            if(inventory != null){
                evaluation.add(inventory.getIngredientName() + " " + String.valueOf(inventory.getQuantity()) + " "
                        + inventory.getUnit() + " - " + String.valueOf(ingredients.get(i).getQuantity()) + " "
                        + ingredients.get(i).getUnit());
            }
            else {
                evaluation.add(ingredients.get(i).getName() + ": not available in Inventory");
            }
        }
        return evaluation;
    }

    private Double calculateCostPrice(Double quantity, Double ingredientQuantity, Double price){
        Double percent = ingredientQuantity / (quantity / 100);
        return percent * price / 100;
    }

    public List<String> inventoryStatus(List<Ingredients> ingredients, Double[] ingredientPrice){
        List<String> resultList = new ArrayList<>();

        for(int i = 0; i < ingredients.size(); ++i){
            Inventory inventory = inventoryRepository.findFirstByIngredientName(ingredients.get(i).getName());
            if(inventory != null){
                if(!inventory.getUnit().equals(ingredients.get(i).getUnit())){
                    if(inventory.getUnit().equals("kg") && ingredients.get(i).getUnit().equals("g") ||
                            inventory.getUnit().equals("l") && ingredients.get(i).getUnit().equals("ml")){
                        System.out.println(inventory.getQuantity() + " _________________ " + (ingredients.get(i).getQuantity() /1000));
                        Double newQuantity = (ingredients.get(i).getQuantity()) / 1000;
                        inventory.setCostPrice(calculateCostPrice(inventory.getQuantity(), newQuantity,
                                inventory.getPrice()));
                        inventory.setPrice(inventory.getPrice() - inventory.getCostPrice());
                        inventory.setQuantity(inventory.getQuantity() - newQuantity);
                        resultList.add(inventory.getQuantity() + " " + inventory.getUnit());
                        inventoryRepository.save(inventory);
                    } else {
                        resultList.add("incomputable");
                    }
                } else {
                    inventory.setCostPrice(calculateCostPrice(inventory.getQuantity(), ingredients.get(i).getQuantity(),
                            inventory.getPrice()));
                    inventory.setPrice(inventory.getPrice() - inventory.getCostPrice());
                    inventory.setQuantity(inventory.getQuantity() - ingredients.get(i).getQuantity());
                    resultList.add(inventory.getQuantity() + " " + inventory.getUnit());
                    inventoryRepository.save(inventory);
                }
                ingredientPrice[i] = inventory.getCostPrice();

            } else {
                resultList.add("0.0");
                ingredientPrice[i] = 0.0;

            }
        }
        return resultList;
    }

    public void addEditedElement(Inventory inventory){
        Inventory oldInventory = inventory;
        inventoryRepository.save(inventory);
        deleteElement(oldInventory.getId());
    }

    public void deleteElement(Long id){
        inventoryRepository.deleteById(id);
    }

    public void updateElement(Inventory inventory){

        inventoryRepository.save(inventory);
    }

    public Optional<Inventory> findElement(Long id){
        return inventoryRepository.findById(id);
    }

}

