package com.example.fuseCanteen.Model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.example.fuseCanteen.common.LocalDateTimeSerializerCs;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by aishwarya on 9/5/20.
 */


public class orderStatsDto {

    private Integer id;
    private String userName;
    private String foodName;
    private Integer orderQty;
    private BigDecimal foodPrice;
    private BigDecimal totalAmount;

    @JsonSerialize(using = LocalDateTimeSerializerCs.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderTime;

    private String orderStatus;
    private String paidStatus;

    public orderStatsDto(Integer id, String userName, String foodName, Integer orderQty, BigDecimal foodPrice, BigDecimal totalAmount, LocalDateTime orderTime, String orderStatus, String paidStatus) {
        this.id = id;
        this.userName = userName;
        this.foodName = foodName;
        this.orderQty = orderQty;
        this.foodPrice = foodPrice;
        this.totalAmount = totalAmount;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.paidStatus = paidStatus;
    }

    public orderStatsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
}
