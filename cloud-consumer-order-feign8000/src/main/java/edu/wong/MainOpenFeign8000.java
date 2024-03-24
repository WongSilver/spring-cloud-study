package edu.wong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 使用consul为注册中心
@EnableFeignClients // 使用openfeign
public class MainOpenFeign8000 {

    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign8000.class, args);
    }
}
