package com.example.fuseCanteen.Model.food;

import java.math.BigDecimal;

/**
 * Created by aishwarya on 9/5/20.
 */
public class foodNameDto {

    private String foodName;
    private BigDecimal foodPrice;
    private String Day;

    public foodNameDto(String foodName, BigDecimal foodPrice, String day) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        Day = day;
    }


    public foodNameDto() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
}
