package com.example.studyapi.config;

import com.example.studyapi.shiro.CustomerRealm;
import com.example.studyapi.shiro.JwtFilter;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration

public class ShiroConfig {

    @Autowired
    JwtFilter jwtFilter;

    /**
     * shiro的默认sessionmanager为其他，将其设置为redisSessionDAO
     *
     * @param redisSessionDAO
     * @return
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setSessionDAO(redisSessionDAO);

        return sessionManager;
    }

    /**
     * 设置安全管理器
     *
     * @param customerRealm
     * @param sessionManager
     * @param redisCacheManager
     * @return
     */

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomerRealm customerRealm,
                                                               SessionManager sessionManager,
                                                               RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();


        webSecurityManager.setSessionManager(sessionManager);

        webSecurityManager.setCacheManager(redisCacheManager);

        webSecurityManager.setRealm(customerRealm);

        return webSecurityManager;
    }

    /**
     * 设置权限校验,此处设置的校验方式是jwt，自定义
     *
     * @return
     */

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/**", "jwt");//通过注解方式校验权限
        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }

    /**
     * 此处添加了自定义的filter，即前面的定义使用的jwt
     * @param defaultWebSecurityManager
     * @param shiroFilterChainDefinition
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);


        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", jwtFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainMap = shiroFilterChainDefinition.getFilterChainMap();

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return shiroFilterFactoryBean;
    }


}
