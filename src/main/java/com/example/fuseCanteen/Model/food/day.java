package com.example.fuseCanteen.Model.food;

import javax.persistence.*;

/**
 * Created by aishwarya on 9/10/20.
 */

@Entity
@Table(name = "days")
public class day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DAY")
    private String dayName;

    public day(String dayName) {
        this.dayName = dayName;
    }

    public day() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
