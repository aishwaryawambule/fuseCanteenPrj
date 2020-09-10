package com.example.fuseCanteen.Model.user;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aishwarya on 9/4/20.
 */


@Entity
@Table(name = "USERS")
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMPLOYEE_NUMBER")
    private String empNumber;

    @Column(name = "IS_ADMIN")
    private Integer isAdmin;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "userroles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns  = @JoinColumn(name = "ROLE_ID")
    )

    private Set<roles> rolesSet = new HashSet<>();

    public users(String userName, String empNumber, Integer isAdmin, Date createdDate, String userPassword, Set<roles> rolesSet) {
        this.userName = userName;
        this.empNumber = empNumber;
        this.isAdmin = isAdmin;
        this.createdDate = createdDate;
        this.userPassword = userPassword;
        this.rolesSet = rolesSet;
    }

    public users(){

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

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<roles> rolesSet) {
        this.rolesSet = rolesSet;
    }
}
