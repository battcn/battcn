package com.battcn.framework.security.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器对外直接暴露URL
 *
 * @author Levin
 * @since 2019-04-04
 */
@Data
@ConfigurationProperties(prefix = "security.oauth2.client")
public class SecurityIgnoreProperties {

    private List<String> ignoreUrls = new ArrayList<>();

}
