package com.example.fuseCanteen.Model.food;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.example.fuseCanteen.Model.user.users;
import com.example.fuseCanteen.Model.days.days;
import com.example.fuseCanteen.common.LocalDateTimeSerializerCs;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

/**
 * Created by aishwarya on 9/7/20.
 */


@SqlResultSetMapping(name = "emplooyeeFoodRequest",classes = {
        @ConstructorResult(targetClass = employeeFoodRequestDto.class, columns = {
                @ColumnResult(name = "USER_NAME", type = String.class),
                @ColumnResult(name = "EMPLOYEE_NUMBER", type = String.class),
                @ColumnResult(name = "FOOD_NAME", type = String.class),
                @ColumnResult(name = "DAY", type = String.class),
                @ColumnResult(name = "DATE_REQUESTED", type = String.class),
                @ColumnResult(name = "DATE_MODIFIED", type = String.class),
                @ColumnResult(name = "REQUEST_ADDED_BY", type = String.class),
                @ColumnResult(name = "REQUEST_MODIFIED_BY", type = String.class),
                @ColumnResult(name = "REQUEST_STATUS", type = String.class),
                @ColumnResult(name = "REMARKS", type = String.class)

        })
})

@Entity
@Table(name = "employeefoodrequest")
public class employeeFoodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FOOD_ID", nullable = false)
    private food food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAY_ID", nullable = false)
    private days days;

    @JsonSerialize(using = LocalDateTimeSerializerCs.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "DATE_REQUESTED")
    private LocalDateTime dateRequested;

    @JsonSerialize(using = LocalDateTimeSerializerCs.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;

    @Column(name = "REQUEST_MODIFIED_BY")
    private String requestModifiedBy;

    @Column(name = "REQUEST_ADDED_BY")
    private String requestAddedBy;

    @Column(name = "REQUEST_STATUS")
    private String requestStatus;

    @Column(name = "REMARKS")
    private String remarks;


    public employeeFoodRequest(com.example.fuseCanteen.Model.food.food food, com.example.fuseCanteen.Model.user.users users, com.example.fuseCanteen.Model.days.days days, LocalDateTime dateRequested, LocalDateTime dateModified, String requestModifiedBy, String requestAddedBy, String requestStatus, String remarks) {
        this.food = food;
        this.users = users;
        this.days = days;
        this.dateRequested = dateRequested;
        this.dateModified = dateModified;
        this.requestModifiedBy = requestModifiedBy;
        this.requestAddedBy = requestAddedBy;
        this.requestStatus = requestStatus;
        this.remarks = remarks;
    }

    public employeeFoodRequest(){

    }

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

    public com.example.fuseCanteen.Model.days.days getDays() {
        return days;
    }

    public void setDays(com.example.fuseCanteen.Model.days.days days) {
        this.days = days;
    }

    public LocalDateTime getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
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

    public String getRequestAddedBy() {
        return requestAddedBy;
    }

    public void setRequestAddedBy(String requestAddedBy) {
        this.requestAddedBy = requestAddedBy;
    }
}
