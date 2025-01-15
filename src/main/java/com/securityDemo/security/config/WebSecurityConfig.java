package com.securityDemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

       httpSecurity
              .csrf(csrf -> csrf.disable()) // disable csrf
              .authorizeHttpRequests( //auth all req
                    request-> request
                            .requestMatchers("register").permitAll()
                            .anyRequest().authenticated()) // any req has to b authenticated
              .formLogin(Customizer.withDefaults())
              .httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails samm = User.withUsername("samm")
                .password("password")
                .roles("USER")
                .build();
        UserDetails nik = User.withUsername("nik")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(samm,nik);
    }
}
