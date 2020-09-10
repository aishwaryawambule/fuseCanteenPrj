package com.example.fuseCanteen.configurations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by aishwarya on 9/8/20.
 */

/**
 * to generate BCrypt password !!!
*/

public class passwordEncoder {

    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin@123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.print(encodedPassword);
    }
}
