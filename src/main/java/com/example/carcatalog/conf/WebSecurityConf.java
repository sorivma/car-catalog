package com.example.carcatalog.conf;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConf {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "users/register").permitAll()
                );

        httpSecurity
                .formLogin()
                .permitAll();


        httpSecurity
                .logout()
                .logoutSuccessUrl("/");

        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/offers/").permitAll()
                .requestMatchers("/offers/details/**").permitAll()
                .requestMatchers("/offers/add").authenticated()
                .requestMatchers("/offers/edit/**").authenticated()
                .requestMatchers("/offers/delete/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/users/**").authenticated();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
