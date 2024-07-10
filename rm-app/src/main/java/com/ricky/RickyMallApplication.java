package com.ricky;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className RickyMallApplication
 * @desc 启动类
 */
// @EnableDubbo
@Configurable
@SpringBootApplication
public class RickyMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickyMallApplication.class, args);
    }

}
