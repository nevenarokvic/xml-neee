package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.fasterxml.jackson.databind.ObjectWriter.Prefetch.empty;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers().frameOptions().disable().and()
                .httpBasic().disable()
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                //                       .anyRequest().authenticated()
//                  .and()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().formLogin()
                .disable();

        //               .rememberMe();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}
