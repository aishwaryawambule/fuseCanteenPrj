package com.example.fuseCanteen.Service.modelService;

import com.example.fuseCanteen.dao.user.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.fuseCanteen.Model.user.users;
import com.example.fuseCanteen.Service.security.password.PasswordService;
import java.util.List;
import java.util.Optional;


/**
 * Created by aishwarya on 9/4/20.
 */

@Service
public class userService {

    @Autowired
    userRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public List<users> getAllUsers(){
        return (List<users>) userRepository.getAllUsers();
    }


    public users findUserByEmpNo(String empNo) {
        return this.userRepository.getUserByempNum(empNo);
    }

    public Optional<users> findUserByUsernameAndPassword(String username, String password,String empNo) {

        users user = this.findUserByEmpNo(empNo);

        return this.passwordService.equals(password, user.getUserPassword()) ? Optional.of(user) : Optional.empty();
    }
}
