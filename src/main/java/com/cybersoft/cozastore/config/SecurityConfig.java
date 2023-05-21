package com.cybersoft.cozastore.config;

import com.cybersoft.cozastore.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//MD5, SHA1, RSA...

    /**
     * Khai báo dạng mã hóa giành cho password
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Tao ra AuthenticationManager de customer lai thong tin chung thuc
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity)throws Exception{
        CustomUserDetailService customUserDetailService = new CustomUserDetailService();

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }
    //Tạo ra danh sách user dùng để chứng thực khi đăng nhập và lưu ở memory
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user = User.withUsername("user1")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
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
                .authorizeHttpRequests()
                    //Quy định chứng thực cho link chỉ định
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    //Tất cả các link còn lại điều phải chứng thực
                    .anyRequest().authenticated()
//                Chức thực dạng Basic Authen
                .and().httpBasic()
                .and().build();

    }

}
