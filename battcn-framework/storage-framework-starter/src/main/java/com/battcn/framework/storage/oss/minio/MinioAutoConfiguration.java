package com.battcn.framework.storage.oss.minio;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 自动配置类
 *
 * @author Levin
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({MinioProperties.class})
public class MinioAutoConfiguration {

    @Bean
    MinioTemplate template(MinioProperties properties) {
        return new MinioTemplate(
                properties.getUrl(),
                properties.getAccessKey(),
                properties.getSecretKey()
        );
    }

}
