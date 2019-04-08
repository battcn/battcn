package com.battcn.auth;

import org.junit.Test;

public class PhoneRegexTest {

    @Test
    public void test1(){
        String regex = "^[1][0-9]{10}$";
        System.out.println("10000000000".matches(regex));
    }

}
