package io.choerodon.swagger;

import org.hzero.autoconfigure.swagger.EnableHZeroSwagger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroSwagger
@EnableDiscoveryClient
@SpringBootApplication
public class HzeroSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HzeroSwaggerApplication.class, args);
    }
}


