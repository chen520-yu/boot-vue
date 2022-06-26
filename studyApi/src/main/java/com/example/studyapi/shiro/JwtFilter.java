package com.example.studyapi.shiro;

import com.example.studyapi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此处涉及shiro的核心认证原理和过程
 * 首先AuthenticatingFilter继承了AuthenticationFilter
 * 实现了onAccessDenied方法
 * <p>
 * 实际上，会先执行AuthenticationFilter中isAccessAllowed方法
 * 当未登录时才会执行onAccessDenied方法
 * <p>
 * 但此处并未实现isAccessAllowed方法，直接写了onAccessDenied方法
 */

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization.isEmpty()) {
            return null;
        }

        return new JwtToken(authorization);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization.isEmpty()) {
            return true;
        } else {
            Claims claims = jwtUtils.getClaimsByToken(authorization);

            if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
                return true;
            }
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));

        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");

        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

// 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
        }

        return super.preHandle(request, response);

    }
}
