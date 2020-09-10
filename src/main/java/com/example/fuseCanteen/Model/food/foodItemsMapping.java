package com.example.fuseCanteen.Model.food;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by aishwarya on 9/4/20.
 */

@Entity
@Table(name = "foodtypemapping")
public class foodItemsMapping  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private foodItems foodItems;

    @Column(name = "FOOD_TYPE")
    private String foodType;

    @Column(name = "FOOD_PRICE",columnDefinition = "Decimal(5,2)")
    private BigDecimal foodPrice;

    @Column(name = "ADDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @Column(name = "MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public foodItems getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(com.example.fuseCanteen.Model.food.foodItems foodItems) {
        this.foodItems = foodItems;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
