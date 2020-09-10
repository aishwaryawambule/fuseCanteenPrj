package com.example.fuseCanteen.Model.food;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aishwarya on 9/4/20.
 */

@Entity
@Table(name = "fooditems")
public class foodItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Integer id;

    @Column(name = "FOOD_NAME")
    private String foodName;

    @Column(name = "FOOD_CODE")
    private String foodCode;

    @Column(name = "ADDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
