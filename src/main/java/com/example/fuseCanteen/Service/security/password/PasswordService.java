package com.example.fuseCanteen.Service.security.password;

import com.example.fuseCanteen.constant.Parameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * Password Service checking whether password entered matches or not for token generation !!
 * */

@Configuration
@Service
public class PasswordService {

    @Value(Parameters.KEY_JWT_SECRET)
    private String secret;
	
    private BCryptPasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}


//	public boolean equals(String rawPassword, String encodedPassword) {
//		Boolean value = this.passwordEncoder.matches(rawPassword, encodedPassword);
//		return value;
//	}


	public String encode(String rawPassword) {
		return this.passwordEncoder.encode(rawPassword);
	}

	public boolean equals(String rawPassword, String encodedPassword) {
//		String encoded = encode(rawPassword);
		return this.passwordEncoder.matches(rawPassword, encodedPassword);
//		return encodedPassword.equals(encoded);

	}
}
