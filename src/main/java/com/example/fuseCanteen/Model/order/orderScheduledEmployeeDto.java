package com.example.fuseCanteen.Model.order;

import com.example.fuseCanteen.common.LocalDateTimeSerializerCs;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by aishwarya on 9/7/20.
 */
public class orderScheduledEmployeeDto {


    private String userName;
    private String employeeNumber;
    private String foodName;
    private Integer orderQty;
    private BigDecimal foodPrice;
    private BigDecimal totalAmt;

    @JsonSerialize(using = LocalDateTimeSerializerCs.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderTime;

    private String orderStatus;
    private String scheduledTime;
    private String paidStatus;

    public orderScheduledEmployeeDto(String userName, String employeeNumber, String foodName, Integer orderQty, BigDecimal foodPrice, BigDecimal totalAmt, LocalDateTime orderTime, String orderStatus, String scheduledTime, String paidStatus) {
        this.userName = userName;
        this.employeeNumber = employeeNumber;
        this.foodName = foodName;
        this.orderQty = orderQty;
        this.foodPrice = foodPrice;
        this.totalAmt = totalAmt;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.scheduledTime = scheduledTime;
        this.paidStatus = paidStatus;
    }

    public orderScheduledEmployeeDto(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
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

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
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

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }
}
