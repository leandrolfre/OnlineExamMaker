package com.techTrial.testMaker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/exam")
          .and()
          .authorizeRequests()
          .antMatchers("/exam").authenticated()
          .antMatchers("/grade").authenticated()
          .anyRequest().permitAll();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .jdbcAuthentication()
          .dataSource(dataSource)
          .usersByUsernameQuery("select username, password, true from user where username=?")
          .authoritiesByUsernameQuery("select username, 'ROLE_USER' from user where username=?")
          .passwordEncoder(encoder());
    }

    @Bean
    public StandardPasswordEncoder encoder() {
        return new StandardPasswordEncoder("Cr0220v3R");
    }

}
