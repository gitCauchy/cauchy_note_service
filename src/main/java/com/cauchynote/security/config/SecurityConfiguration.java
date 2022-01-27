package com.cauchynote.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cauchynote.system.service.impl.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Cauchy
 * @ClassName SecurityConfiguration.java
 * @Date 2021年8月9日
 * @Description Spring-Security配置类
 * @Version V1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                //.antMatchers("/user/addUser").hasAuthority("USER_ADD")
//                //.antMatchers("/article/updateUser").hasAuthority("USER_MOD")
//                //.antMatchers("/article/deleteUser").hasAuthority("USER_DEL")
//                //.antMatchers("/article/getUser").hasAuthority("USER_QRY")
//                //.antMatchers("/**")
//                //.authenticated()
//                //.fullyAuthenticated()
//                .antMatchers("/user/*", "/home", "/logon")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/index")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll().and().csrf().disable();
    }


}
