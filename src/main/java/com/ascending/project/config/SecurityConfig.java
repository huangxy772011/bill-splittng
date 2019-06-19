package com.ascending.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Step 1
    // test requests, set username and password in server memory
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("{noop}password").roles("REGISTERED_ROLE");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()  //cross-site request forgery
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()    //http builder syntax
//                .formLogin();  //any requests need to go through authentication and it cross by a form login
//    }

    //Step 2 assign role to login user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user")
                .password("password").roles("REGISTERED_ROLE");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/users").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

}
