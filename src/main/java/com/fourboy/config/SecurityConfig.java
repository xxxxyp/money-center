package com.fourboy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourboy.bean.res.BaseObj;
import com.fourboy.filter.CustomerAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * spring security 有三种账号密码配置方式
     * 1、在 application.properties 中进行配置
     * 2、通过 Java 代码配置在内存中  此处用这种方式配置账号密码
     * 3、通过 Java 从数据库中加载
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").roles("admin").password("$2a$10$oRrPIB9m2vhPbWxBTU3/CO/jwF7FPb9YAuyS/Bhix5HbiOWVEdU2K");
    }


    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        BaseObj<Object> baseObj = new BaseObj<>();
                        baseObj.setMessage("注销成功" );
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(baseObj));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
                        BaseObj<Object> baseObj = new BaseObj<>();
                        baseObj.setMessage("权限不足" );
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(baseObj));
                        out.flush();
                        out.close();
                    }
                });
        http.addFilterAt(customerAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    CustomerAuthenticationFilter customerAuthenticationFilter() throws Exception{
        final CustomerAuthenticationFilter filter = new CustomerAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                BaseObj<Object> baseObj = new BaseObj<>();
                baseObj.setMessage("登陆成功" + auth.getPrincipal());
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(baseObj));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                BaseObj<Object> baseObj = new BaseObj<>();
                baseObj.setMessage("登陆失败" );
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(baseObj));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    /**
     * 密码加密方式，采用BCryptPasswordEncoder
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        String don1995 = new BCryptPasswordEncoder().encode("don1995");
        System.out.println(don1995);
    }
}
