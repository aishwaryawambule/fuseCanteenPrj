package com.example.fuseCanteen.configurations;

/**
 * Created by aishwarya on 9/8/20.
 */

//import com.example.fuseCanteen.Service.modelService.userDetailsServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    userDetailsServiceImp userDetailsServiceImp;
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new userDetailsServiceImp();
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        return authenticationProvider;
//    }
//
////    @Bean
////    public JwtAuthenticationFilter authenticationTokenFilterBean() {
////        return new JwtAuthenticationFilter();
////    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authentication) throws
//            Exception{
//        authentication.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("Yabesh").password("admin@123")
//                .authorities("ADMIN")
//                .and()
//                .passwordEncoder(passwordEncoder())
//                .withUser("Ruben").password("employee@123")
//                .authorities("ROLE_USER");
//    }
//
//
//    @Override
//    protected void  configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeRequests()
//                .antMatchers("/api/users/all").hasAnyRole("ADMIN")
//                .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//                .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//                .antMatchers("/delete/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
////                .formLogin().permitAll()
////                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//        ;
//    }
//
//
//}


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.fuseCanteen.constant.Parameters;
import com.example.fuseCanteen.Service.security.jwt.JWTService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtTokenService;

    @Value(Parameters.KEY_JWT_AUTH_HEADER)
    String authHeader;

    @Value(Parameters.KEY_SECURITY_OPEN_API_PATTERN)
    String openApiPattern;

    @Value(Parameters.KEY_SECURITY_SECURE_API_PATTERN)
    String secureApiPattern;

    @Value(Parameters.KEY_SECURITY_CREATE_API_PATTERN)
    String userApiPattern;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(this.openApiPattern);
        web.ignoring().antMatchers(this.userApiPattern);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(this.secureApiPattern)
                .hasRole(Parameters.ROLE_ADMIN)
                .and()
                .addFilterBefore(new JWTFilter(this.secureApiPattern, jwtTokenService, authHeader), UsernamePasswordAuthenticationFilter.class);

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(this.userApiPattern)
                .hasRole(Parameters.ROLE_ADMIN)
                .and()
                .addFilterBefore(new JWTFilter(this.userApiPattern, jwtTokenService, authHeader), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT USER_NAME,USER_PASSWORD FROM users where EMPLOYEE_NUMBER=?")
                .authoritiesByUsernameQuery("SELECT * FROM users u JOIN userroles ur on ur.USER_ID = u.ID JOIN roles r on r.ID = ur.ROLE_ID " +
                        "WHERE u.EMPLOYEE_NUMBER = ?")
                .passwordEncoder(this.passwordEncoder);
    }
}

