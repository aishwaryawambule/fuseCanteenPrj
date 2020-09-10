package com.example.fuseCanteen.Model.food;

/**
 * Created by aishwarya on 9/7/20.
 */
public class foodRequest {

    private Integer id;
    private String foodName;
    private String remarks;


    public foodRequest(Integer id, String foodName, String remarks) {
        this.id = id;
        this.foodName = foodName;
        this.remarks = remarks;
    }

    foodRequest(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
