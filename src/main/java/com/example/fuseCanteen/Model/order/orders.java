package com.example.fuseCanteen.Model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.fuseCanteen.Model.food.*;
import com.example.fuseCanteen.Model.user.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by aishwarya on 9/5/20.
 */



@SqlResultSetMappings({
        @SqlResultSetMapping(name = "orderStats", classes = {
                @ConstructorResult(targetClass = orderStatsDto.class, columns = {
                        @ColumnResult(name = "ID", type = int.class),
                        @ColumnResult(name = "USER_NAME", type = String.class),
                        @ColumnResult(name = "FOOD_NAME", type = String.class),
                        @ColumnResult(name = "ORDER_QTY", type = Integer.class),
                        @ColumnResult(name = "FOOD_PRICE", type = BigDecimal.class),
                        @ColumnResult(name = "TOTAL_AMT", type = BigDecimal.class),
                        @ColumnResult(name = "ORDER_TIME", type = LocalDateTime.class),
                        @ColumnResult(name = "ORDER_STATUS", type = String.class),
                        @ColumnResult(name = "PAID_STATUS", type = String.class)

                })
        }),
        @SqlResultSetMapping(name = "orderByPopularity", classes = {
                @ConstructorResult(targetClass = orderByPopularityDto.class, columns = {
                        @ColumnResult(name = "FOOD_NAME", type = String.class),
                        @ColumnResult(name = "TOTAL_QTY", type = Integer.class),

                })
        }),
        @SqlResultSetMapping(name = "orderTotalByEmployee", classes = {
                @ConstructorResult(targetClass = totalOrderEmployeeDto.class, columns = {
                        @ColumnResult(name = "USER_NAME", type = String.class),
                        @ColumnResult(name = "EMPLOYEE_NUMBER", type = String.class),
                        @ColumnResult(name = "TOTAL_AMT", type = BigDecimal.class),
                        @ColumnResult(name = "ORDER_TIME", type = LocalDateTime.class),
                        @ColumnResult(name = "PAID_STATUS", type = String.class)

                })
        }),
        @SqlResultSetMapping(name = "orderDescForEmployee", classes = {
                @ConstructorResult(targetClass = orderDescEmployeeDto.class, columns = {
                        @ColumnResult(name = "USER_NAME", type = String.class),
                        @ColumnResult(name = "EMPLOYEE_NUMBER", type = String.class),
                        @ColumnResult(name = "FOOD_NAME", type = String.class),
                        @ColumnResult(name = "ORDER_QTY", type = Integer.class),
                        @ColumnResult(name = "FOOD_PRICE", type = BigDecimal.class),
                        @ColumnResult(name = "TOTAL_AMT", type = BigDecimal.class),
                        @ColumnResult(name = "ORDER_TIME", type = LocalDateTime.class),
                        @ColumnResult(name = "PAID_STATUS", type = String.class),

                })
        }),
        @SqlResultSetMapping(name = "orderScheduledEmployee", classes = {
                @ConstructorResult(targetClass = orderScheduledEmployeeDto.class, columns = {
                        @ColumnResult(name = "USER_NAME", type = String.class),
                        @ColumnResult(name = "EMPLOYEE_NUMBER", type = String.class),
                        @ColumnResult(name = "FOOD_NAME", type = String.class),
                        @ColumnResult(name = "ORDER_QTY", type = Integer.class),
                        @ColumnResult(name = "FOOD_PRICE", type = BigDecimal.class),
                        @ColumnResult(name = "TOTAL_AMT", type = BigDecimal.class),
                        @ColumnResult(name = "ORDER_TIME", type = LocalDateTime.class),
                        @ColumnResult(name = "ORDER_STATUS", type = String.class),
                        @ColumnResult(name = "SCHEDULED_TIME", type = String.class),
                        @ColumnResult(name = "PAID_STATUS", type = String.class)

                })
        }),
})


@Getter
@Setter
@Entity
@Table(name = "orders")
public class orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private food food;

    @ManyToOne()
    @JoinColumn(name = "USER_ID", nullable = false)
    private users users;

    @Column(name = "ORDER_QTY")
    private Integer orderQty;

    @Column(name = "ORDER_PRICE")
    private BigDecimal orderPrice;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "ORDER_TIME")
    private LocalDateTime orderTime;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @Column(name = "EMPOLYEE_NUMBER")
    private String employeeNumber;

    @Column(name = "PAID_STATUS")
    private String paidStatus;


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

    public com.example.fuseCanteen.Model.user.users getUsers() {
        return users;
    }

    public void setUsers(com.example.fuseCanteen.Model.user.users users) {
        this.users = users;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
