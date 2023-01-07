package com.yali.vilivili.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurity配置
 *
 * @author fuqianlin
 * @date 2023-01-06 17:25
 **/

@Configuration
public class WebSecurityConfig {


    /**
     * 密码加密
     *
     * @return {@link PasswordEncoder} 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置Spring Security的http安全
     *
     * @throws Exception 异常
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                //放行所有接口

                                //springboot2.7写法
                                .antMatchers("/user/**").permitAll()
                                .antMatchers("/user/updateAndSaveUser").permitAll()
                                .antMatchers("/user/login").permitAll()
                                .antMatchers("/user/register").permitAll()
                                .antMatchers("/static/**", "/resources/**").permitAll()
                                .antMatchers("/swagger/**","v2/**","/v2/api-docs", "/swagger-resources/configuration/ui",
                                        "/swagger-resources", "/swagger-resources/configuration/security",
                                        "/swagger-ui.html", "/doc.html","/webjars/**").permitAll()
                                .antMatchers("/swagger/**").permitAll()
                                .antMatchers("/swagger-ui.html").permitAll()
                                .antMatchers("/webjars/**").permitAll()
                                .antMatchers("/v2/**").permitAll()
                                .antMatchers("/v2/api-docs-ext/**").permitAll()
                                .antMatchers("/swagger-resources/**").permitAll()
                                .antMatchers("/doc.html").permitAll()
                              //springboot3.0写法
//                                .requestMatchers("/user/**").permitAll()
//                                .requestMatchers("/user/updateAndSaveUser").permitAll()
//                                // 放行登录接口
//                                .requestMatchers("/user/login").permitAll()
//                                // 放行注册接口
//                                .requestMatchers("/user/register","").permitAll()
//                                // 放行资源目录
//                                .requestMatchers("/static/**", "/resources/**").permitAll()
                                // 其余的都需要权限校验
                                .anyRequest().authenticated()
                                // 防跨站请求伪造
                                .and().csrf(AbstractHttpConfigurer::disable);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).build();
    }

}

