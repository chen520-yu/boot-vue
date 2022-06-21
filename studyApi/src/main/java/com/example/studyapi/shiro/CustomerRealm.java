package com.example.studyapi.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.example.studyapi.entity.User;
import com.example.studyapi.service.UserService;
import com.example.studyapi.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimsByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(userId);

        if (user == null) {
            throw new UnknownAccountException("用户信息不存在");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("用户已被锁定");
        }

        UserProfile userProfile = new UserProfile();

        BeanUtil.copyProperties(user, userProfile);

        return new SimpleAuthenticationInfo(userProfile, jwtToken.getCredentials(), getName());

    }
}
