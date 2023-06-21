package RecipeGeneratorApp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double quantity;
    private String unit;
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;
    public Ingredients() {
    }

    public Ingredients(String name, String unit, Double quantity){
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
