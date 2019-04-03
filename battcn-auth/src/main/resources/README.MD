
1、如果请求头设置了 `Authorization` 的字段，那么就可以不用传 `client_id` 和 `client_secret` 两个字段了，`Spring Security` 中
 `BasicAuthenticationFilter` 会去进行解析处理，解析规则默认是 `Base64.getDecoder().decode(Authorization)` 意味着它不是足够安全的，
 此处可以重写 `BasicAuthenticationFilter` 修改加解密规则即可
 
``` json
[{"key":"Authorization","value":"Basic dGVzdDp0ZXN0"}]
```


2、Token 增强机制

默认情况下，`Spring Security` 只返回了如下几个字段

``` json
{
    "access_token": "e4080026-9ad1-47f5-8f04-69795523862d",
    "token_type": "bearer",
    "refresh_token": "4e34f19d-8ff0-4fd7-9609-a62a7f012912",
    "expires_in": 43199,
    "scope": "server"
}
```

如果想额外扩展返回的值可以在 `AuthorizationServerEndpointsConfigurer` 中设置 `TokenEnhancer`

``` json
{
    "access_token": "e4080026-9ad1-47f5-8f04-69795523862d",
    "token_type": "bearer",
    "refresh_token": "4e34f19d-8ff0-4fd7-9609-a62a7f012912",
    "expires_in": 43199,
    "scope": "server",
    "tenant_id": 0,
    "code": 200,
    "user_id": 1,
    "qq_open_id": "o_0FT0uyg_H1vVy2H0JpSwlVGhWQ",
    "wx_open_id": null,
    "username": "admin"
}
```