package com.example.fuseCanteen.Model.food;

import javax.persistence.*;

/**
 * Created by aishwarya on 9/10/20.
 */

@Entity
@Table(name = "fooddaymapping")
public class foodDayMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private food food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAY_ID", nullable = false)
    private day day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.example.fuseCanteen.Model.food.food getFood() {
        return food;
    }

    public void setFood(com.example.fuseCanteen.Model.food.food food) {
        this.food = food;
    }

    public com.example.fuseCanteen.Model.food.day getDay() {
        return day;
    }

    public void setDay(com.example.fuseCanteen.Model.food.day day) {
        this.day = day;
    }
}
