package com.codeup.codeupspringblog.config;


import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
//          USERS
            .requestMatchers(
            "/posts/create",
            "/posts/*/comment",
            "/posts/*/edit",
            "/posts/*/delete",
            "/posts/profile",
            "/posts/logout").authenticated()

//          VISITORS
            .requestMatchers("/", "/register", "/posts", "/{id}").permitAll()

//          RESOURCES
            .requestMatchers("/css/**", "/js/**").permitAll()
        );
        http.formLogin(withDefaults());
        http.formLogin(login -> login
                .loginPage("/posts/login")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/posts/logout")
                .logoutSuccessUrl("/login"));
        http.httpBasic(withDefaults());
        return http.build();
    }

//        GOD ROLE
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests
//                  .anyRequest().permitAll());
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();
//    }


    @Bean
    public PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance();
    }
}