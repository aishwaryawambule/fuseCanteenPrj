package com.example.fuseCanteen.Service.modelService;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.example.fuseCanteen.Model.user.*;
import org.springframework.stereotype.Service;

/**
 * Created by aishwarya on 9/8/20.
 */

public class userDetails extends users implements UserDetails {

    private users user;

    public userDetails(users user){
        this.user = user;
    }

    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities(){
        Set<roles> rolesSet = user.getRolesSet();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (roles roles1: rolesSet){
            authorities.add(new SimpleGrantedAuthority(roles1.getRoleName()));
        }

        return authorities;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
