package com.example.fuseCanteen.Service.modelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.fuseCanteen.Model.user.*;
import org.springframework.stereotype.Service;

/**
 * Created by aishwarya on 9/8/20.
 */

@Service
public class userDetailsServiceImp implements UserDetailsService {

    @Autowired
    private com.example.fuseCanteen.dao.user.userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        users users = userRepository.getUserByUserName(userName);

        if(users == null){
            throw new UsernameNotFoundException("Sorry, Could not find user!!");

        }
        return new userDetails(users);
    }

    public UserDetails loadUserById(Integer userId) throws UsernameNotFoundException {
        users users = userRepository.getUserById(userId);

        if(users == null){
            throw new UsernameNotFoundException("Sorry, Could not find user!!");

        }
        return new userDetails(users);
    }


}
