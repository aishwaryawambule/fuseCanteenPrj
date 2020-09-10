package com.example.fuseCanteen.Model.food;

import java.time.LocalDateTime;

/**
 * Created by aishwarya on 9/7/20.
 */
public class employeeFoodRequestDto {

    private String userName;
    private String empNum;
    private String foodName;
    private String  day;
    private String dateReq;
    private String dateMod;
    private String requestAddedBy;
    private String requestModifiedBy;
    private String requestStatus;
    private String remarks;

    public employeeFoodRequestDto(String userName, String empNum, String foodName, String day, String dateReq, String dateMod, String requestAddedBy, String requestModifiedBy, String requestStatus, String remarks) {
        this.userName = userName;
        this.empNum = empNum;
        this.foodName = foodName;
        this.day = day;
        this.dateReq = dateReq;
        this.dateMod = dateMod;
        this.requestAddedBy = requestAddedBy;
        this.requestModifiedBy = requestModifiedBy;
        this.requestStatus = requestStatus;
        this.remarks = remarks;
    }

    public employeeFoodRequestDto(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDateReq() {
        return dateReq;
    }

    public void setDateReq(String dateReq) {
        this.dateReq = dateReq;
    }

    public String getDateMod() {
        return dateMod;
    }

    public void setDateMod(String dateMod) {
        this.dateMod = dateMod;
    }

    public String getRequestAddedBy() {
        return requestAddedBy;
    }

    public void setRequestAddedBy(String requestAddedBy) {
        this.requestAddedBy = requestAddedBy;
    }

    public String getRequestModifiedBy() {
        return requestModifiedBy;
    }

    public void setRequestModifiedBy(String requestModifiedBy) {
        this.requestModifiedBy = requestModifiedBy;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
