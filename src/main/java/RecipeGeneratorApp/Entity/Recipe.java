package RecipeGeneratorApp.Entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "recipesDB")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;
    private String ingredientsString;
    private String ingredientsAndQuantities;
    private int serves = 1;
    private String category;
    private String difficulty;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredients> ingredients = new ArrayList<>();
    public Recipe(String name, String text, String ingredientsString, String ingredientsAndQuantities,
                  String difficulty, String category, int serves) {
        this.name = name;
        this.text = text;
        this.ingredientsString = ingredientsString;
        this.serves = serves;
        this.ingredientsAndQuantities = ingredientsAndQuantities;
        this.difficulty = difficulty;
        this.category = category;
    }
    public Recipe(){

    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getIngredientsAndQuantities() {
        return ingredientsAndQuantities;
    }

    public void setIngredientsAndQuantities(String ingredientsAndQuantities) {
        this.ingredientsAndQuantities = ingredientsAndQuantities;
    }

    public String getIngredientsString() {
        return ingredientsString;
    }

    public void setIngredientsString(String ingredientsString) {
        this.ingredientsString = ingredientsString;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
