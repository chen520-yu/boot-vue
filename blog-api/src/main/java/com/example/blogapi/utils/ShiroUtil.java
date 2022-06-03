package com.example.blogapi.utils;

import com.example.blogapi.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

import java.security.Security;

public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
