package com.battcn.auth.service;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    //@Cacheable(value = "clients", key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }


}
