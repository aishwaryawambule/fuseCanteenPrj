package com.example.fuseCanteen.bean;

/**
 * Created by aishwarya on 9/9/20.
 */

/**
 * This class receives the username and password from the request.
 *
 */

public class AuthenticationBean {

    private String username;
    private String password;
    private String empNo;

    public AuthenticationBean(){}

    public AuthenticationBean(String username, String password, String empNo) {
        this.username = username;
        this.password = password;
        this.empNo = empNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
}
