package com.battcn.auth.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

//@Service
//public class ClientDetailsServiceImpl extends JdbcClientDetailsService {
//
//    public ClientDetailsServiceImpl(DataSource dataSource) {
//        super(dataSource);
//    }
//
//    /**
//     * 重写原生方法支持redis缓存
//     *
//     * @param clientId
//     * @return
//     * @throws InvalidClientException
//     */
//    @Override
//    @Cacheable(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
//    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
//        return super.loadClientByClientId(clientId);
//    }
//
//
//}
