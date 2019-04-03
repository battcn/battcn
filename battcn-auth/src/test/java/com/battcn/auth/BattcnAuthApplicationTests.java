package com.battcn.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BattcnAuthApplicationTests {

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    public void clientId() throws UnsupportedEncodingException {
        String auth = new String(Base64.getEncoder().encode("test:test".getBytes()), "UTF-8");
        System.out.println(auth);
    }
    // Base64.getDecoder().
}
