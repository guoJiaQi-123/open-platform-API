package com.tyut.apiclientsdk.config;

import com.tyut.apiclientsdk.client.APIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @version v1.0
 * @author OldGj 2024/9/11
 * @apiNote api client 配置类
 */
@Configuration
@ConfigurationProperties("mazai.api")
@ComponentScan
@Data
public class MaZaiAPIClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public APIClient getApiClient() {
        return new APIClient(accessKey, secretKey);
    }
}
