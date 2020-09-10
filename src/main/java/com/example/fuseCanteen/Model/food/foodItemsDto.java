package com.example.fuseCanteen.Model.food;

import java.math.BigDecimal;

/**
 * Created by aishwarya on 9/4/20.
 */
public class foodItemsDto {

    private String foodCode;
    private String foodName;
    private String foodType;
    private BigDecimal foodPrice;


    public foodItemsDto() {
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }
}
