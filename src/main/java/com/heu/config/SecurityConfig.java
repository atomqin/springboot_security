package com.heu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: qt
 * @Date: 2020/10/31 12:33
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 添加用户和角色
     * user 登陆只拥有角色 USER,admin 登陆拥有角色 USER 和 ADMIN
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPassWordEncoder())
                .withUser("user").password(new MyPassWordEncoder().encode("000")).roles("USER")
                .and()
                .withUser("admin").password(new MyPassWordEncoder().encode("123")).roles("USER", "ADMIN");
    }

    /**
     * ADMIN 拥有访问 index.html 和 admin.html 的权限， USER 只有访问 index.html 的权限
     * 这里反向编写，访问 index.html 的角色两个都可以，访问 admin.html 只有 ADMIN 才可以
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/index").access("hasRole('USER') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
