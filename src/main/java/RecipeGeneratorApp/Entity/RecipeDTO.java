package RecipeGeneratorApp.Entity;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {
    private Long id;
    private String name;
    private String text;
    private String ingredientsString;
    private String ingredientsAndQuantities;
    private int serves = 1;
    private String category;
    private String difficulty;
    private List<String> ingredientName;
    private List<String> ingredientQuantity;
    private List<String> ingredientUnit;

    public RecipeDTO(){
    }

    public RecipeDTO(Long id, String name, String text, String ingredientsString, String ingredientsAndQuantities,
                     int serves, String category, String difficulty, List<String> ingredientName,
                     List<String> ingredientQuantity, List<String> ingredientUnit){
        this.id = id;
        this.name = name;
        this.text = text;
        this.ingredientsString = ingredientsString;
        this.ingredientsAndQuantities = ingredientsAndQuantities;
        this.serves = serves;
        this.category = category;
        this.difficulty = difficulty;
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIngredientsString() {
        return ingredientsString;
    }

    public void setIngredientsString(String ingredientsString) {
        this.ingredientsString = ingredientsString;
    }

    public String getIngredientsAndQuantities() {
        return ingredientsAndQuantities;
    }

    public void setIngredientsAndQuantities(String ingredientsAndQuantities) {
        this.ingredientsAndQuantities = ingredientsAndQuantities;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(List<String> ingredientName) {
        this.ingredientName = ingredientName;
    }

    public List<String> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(List<String> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public List<String> getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(List<String> ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }
}
