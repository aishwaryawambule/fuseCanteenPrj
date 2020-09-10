package com.example.fuseCanteen.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fuseCanteen.Model.user.users;
import com.example.fuseCanteen.Service.modelService.userService;

import java.util.List;

/**
 * Created by aishwarya on 9/4/20.
 */

@RestController
@RequestMapping("/api")
public class userRest {

    @Autowired
    public userService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(users.class);

    @GetMapping("secure/all-users")
    public ResponseEntity getAllUsers(){
        try{
            List<users> usersList = userService.getAllUsers();
            return  ResponseEntity.status(200).body(usersList);
        }
        catch (Exception e){
            LOGGER.error("Unable to fetch users data: "+ e.toString());
            return ResponseEntity.status(500).body(e.toString());
        }
    }
}
