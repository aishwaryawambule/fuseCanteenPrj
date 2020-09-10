package com.example.fuseCanteen.Model.days;

import javax.persistence.*;

/**
 * Created by aishwarya on 9/7/20.
 */

@Entity
@Table(name = "days")
public class days {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DAY")
    private String day;

    public days(String day) {
        this.day = day;
    }

    public days(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
