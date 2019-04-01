package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MultiHttpSecurityConfiguration {

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(customUserService()).passwordEncoder(bCryptPasswordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .antMatchers("/reception/login", "/reception/register", "/user/user_add")
                    .permitAll();

            http.authorizeRequests()
                    .antMatchers("/reception/index", "/reception/findById", "/reception/addArticle",
                            "/reception/personal", "/changeInformation", "/reception/findall", "/reception/findAllByCategory")
                    .hasRole("normal");

            http.csrf().disable();

            http.formLogin()
                    .loginPage("/reception/login")
                    .successForwardUrl("/reception/index")
                    .failureForwardUrl("/reception/login?error");

            http.logout()
                    .logoutUrl("/reception/logout")
                    .logoutSuccessUrl("/reception/login");


            http
                    .antMatcher("/reception/**")
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .httpBasic();
        }


    }

    @Configuration
    public static class AppLoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            //任何用户都可以访问以下URI
            http.authorizeRequests()
                    .antMatchers("/login", "/register", "/user/user_add")
                    .permitAll();

            //其他URI均需要权限校验
            http.authorizeRequests()
                    .anyRequest()
                    .hasAnyRole("administrator", "admin");

            //启用记住密码
            http.authorizeRequests()
                    .and()
                    .rememberMe();

            //关闭CSRF跨站请求伪造
            http.csrf().disable();

            //登录界面设置
            http.formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/index")
                    .failureForwardUrl("/login?error");

            //注销设置
            http.logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
        }
    }
}
