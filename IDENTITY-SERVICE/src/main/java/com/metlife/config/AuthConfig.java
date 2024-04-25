//package com.metlife.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class AuthConfig {
//
//
//
//
//    @Autowired
//  private   PasswordEncoder passwordEncoder;
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("rahul")
//                .password(passwordEncoder.encode("rahul@123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user1 = User.builder()
//                .username("arvind")
//                .password(passwordEncoder.encode("arvind@123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("raj")
//                .password(passwordEncoder.encode("raj@123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,user1,user2);
//    }
//
//
//}
