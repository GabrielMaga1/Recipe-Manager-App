package RecipeGeneratorApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ingredientName;
    private String unit;
    private Double quantity = 0.0;
    private Double price;
    private Double costPrice = 0.0;

    public Inventory(String ingredientName, String unit, Double quantity, Double price) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
    }

    public Inventory(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Double getQuantity() {
        return Math.round(quantity * 10) / 10.0;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return Math.round(price * 10) / 10.0;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCostPrice() {
        return Math.round(costPrice * 10) / 10.0;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
}
