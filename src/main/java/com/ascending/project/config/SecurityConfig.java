package com.ascending.project.config;

import com.ascending.project.extend.security.JwtAuthenticationFilter;
import com.ascending.project.extend.security.RestAuthenticationEntryPoint;
import com.ascending.project.extend.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

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
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//        auth.inMemoryAuthentication().withUser("user")
//                .password("password").roles("REGISTERED_ROLE");
    }

    //TODO
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf().disable().authorizeRequests().antMatchers("/api/users/login", "/api/user", "/api/user/login").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .formLogin();
//                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint); //add entry point
    }

//    //TODO
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.
//    }

}
