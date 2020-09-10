package com.example.fuseCanteen.Model.food;

        import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
        import com.fasterxml.jackson.databind.annotation.JsonSerialize;
        import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
        import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.math.BigDecimal;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by aishwarya on 9/4/20.
 */

@Entity
@Table(name = "foods")
public class food implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FOOD_NAME")
    private String foodName;

    @Column(name = "FOOD_CODE")
    private String foodCode;

    @Column(name = "FOOD_PRICE")
    private BigDecimal foodPrice;


    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @Column(name = "MODIFIED")
    private LocalDateTime modifiedDate;

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

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

}
