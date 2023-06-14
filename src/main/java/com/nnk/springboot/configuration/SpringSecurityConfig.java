package com.nnk.springboot.configuration;

import com.nnk.springboot.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Login Security configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .requestMatchers("/","/login","/css/**", "/jquery/**", "/images/**", "/home").permitAll()
                .requestMatchers("/bidList","/curvePoint","/rating","/ruleName","/trade").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/user").permitAll()
                .anyRequest().authenticated()
                .and().userDetailsService(customUserDetailsService)
                .formLogin().loginPage("/login").permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        return http.build();
    }

}
