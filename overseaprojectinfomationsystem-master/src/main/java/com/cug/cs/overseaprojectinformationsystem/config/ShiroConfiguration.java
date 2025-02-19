package com.cug.cs.overseaprojectinformationsystem.config;

import com.cug.cs.overseaprojectinformationsystem.config.realm.AdminRealm;
import com.cug.cs.overseaprojectinformationsystem.config.realm.UserReaml;
import com.cug.cs.overseaprojectinformationsystem.filter.JwtFilter;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @description: TODO
 * @author: ShengHui
 * @date: 2023-09-01  23:55
 */
@Configuration
public class ShiroConfiguration {
    
    @Autowired
    AuthorizingRealm adminRealm;
    @Autowired
    AuthorizingRealm userReaml;
    /**
     *  注册shiroFilter
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author huangshenghui
     * @since 2023/09/01 23:56
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
    
    
        // 提供 Filter 链, 配置的内容类似于之前的 HandlerInterceptor
        // 1: Filter 是谁: anon, authc, perms
        // 2: Filter 的作用: 建立 url 请求和 Filter 之间的映射关系
        // 3: Filter 之间的顺序: 书写顺序, 通常 anon <- authc <- perms, anon 写在最前面
    
        // 保证 map 中的值是有序的
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // map 中的 key   -> 请求 url
        // map 中的 value -> filter 名称
        // TODO 需要修改请求路径
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        filterChainDefinitionMap.put("/user/kaptcha", "anon");
        //filterChainDefinitionMap.put("/admin/auth/info", "anon");
        // filterChainDefinitionMap.put("/admin/auth/noAuthc", "anon");
        // filterChainDefinitionMap.put("/admin/user/list", "anon");
    
        // filterChainDefinitionMap.put("/wx/auth/login", "anon");
        // filterChainDefinitionMap.put("/wx/auth/logout", "anon");
    
        // 访问 /admin/user/list 需要的是 aaa 这个权限
        // 通常不这样写, 增加权限通常指的 url -> 对 handler 方法做访问控制
        // 耦合 handler 方法和权限做耦合
        // filterChainDefinitionMap.put("/admin/user/list", "perms[aaa]");
    
        filterChainDefinitionMap.put("/**", "anon");
    
        // 如果访问某个请求没有权限, shiro 会做重定向 -> 默认重定向地址是 /login.jsp
        // 如果想要修改重定向地址, 可以使用
        // filterFactoryBean.setLoginUrl("/admin/auth/login");
        // 添加自定义过滤器(所有请求通过自定义的jwtFilter)
        // 下面的代码是自定义jwt过滤器，请求执行顺序是shiro-->jwt过滤器-->内部请求
        // 不大开下面代码，请求直接shiro-->内部请求
        // anon,biaoshi所有人都能访问，不需要登录验证  /**匹配的是所有请求不包括前面单独零出来的
        // 因为这是个chain，前面的先匹配 再匹配后面的代码（开发时可以注释掉下方，生产中放开）
        /*HashMap<String, Filter> map = new HashMap<>(1);
        map.put("jwt",new JwtFilter());
        filterFactoryBean.setFilters(map);
        filterChainDefinitionMap.put("/**","jwt");*/
        
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    
        return filterFactoryBean;
    }
    
    @Bean
    public DefaultWebSecurityManager securityManager( DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 给 SecurityManager, 默认认证器, 默认授权器都设置了 Realms
        // securityManager.setRealms(Arrays.asList(realm));
        securityManager.setRealms(Arrays.asList(adminRealm,userReaml));
        
        // 如果要使用自定义的认证器和授权器, 需要单独使用 set 方法, 还需要给自定义的认证器和授权器单独提供 Realms
        // securityManager.setAuthenticator();
        // securityManager.setAuthorizer();
        
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    
    @Bean
    public SystemSessionManager sessionManager() {
        // return new DefaultWebSessionManager();
        return new SystemSessionManager();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
