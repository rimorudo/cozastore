package com.cybersoft.cozastore.config;

import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.provider.CustomerAuthenManagerProvider;
import com.cybersoft.cozastore.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//MD5, SHA1, RSA...
    @Autowired
    CustomerAuthenManagerProvider customerAuthenManagerProvider;

    @Autowired
    JwtFilter jwtFilter;
    /**
     * Khai báo dạng mã hóa giành cho passwor
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Tao ra AuthenticationManager de customer lai thong tin chung thuc
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity)throws Exception{

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customerAuthenManagerProvider)
                .build();
    }
//    Custom lại thông tin cấu hình của spring security
    /**
     * Java 8, 11 : antMatchers
     * Java 17~ : requestMatcher
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
//             Cho phép cấu hình chứng thực cho các request
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    //Tất cả các link còn lại điều phải chứng thực
                    .anyRequest().authenticated()
//                Chức thực dạng Basic Authen
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
