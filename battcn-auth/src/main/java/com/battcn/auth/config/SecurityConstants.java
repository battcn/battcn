package com.battcn.auth.config;

/**
 * @author Levin
 * @since 2019-04-03
 */
public interface SecurityConstants {


    String AUTH_TYPE = "auth_type";

    /**
     * 手机号登录URL
     */
    String MOBILE_TOKEN_URL = "/mobile/token";

    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";


    /**
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";


    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 为什么要设置自己的呢？主要目的是为了存储的时候可以不用存 {noop} 這个东西，因为 oauth2 解析 client 的时候会去解析是否存在 {} 花括号
     */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";
}
