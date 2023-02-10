package com.itheima.sh.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
    公共配置类
 */
@Configuration
public class CommonConfig {

    /**
     * 密码加密器
     * BCryptPasswordEncoder方法采用SHA-256对密码进行加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //BCryptPasswordEncoder是接口PasswordEncoder实现类
        return new BCryptPasswordEncoder();
    }

}
