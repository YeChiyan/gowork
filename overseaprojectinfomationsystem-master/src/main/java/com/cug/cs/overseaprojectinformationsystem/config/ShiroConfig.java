package com.cug.cs.overseaprojectinformationsystem.config;

import com.cug.cs.overseaprojectinformationsystem.config.realm.AdminRealm;
import com.cug.cs.overseaprojectinformationsystem.config.realm.SuperAdminRealm;
import com.cug.cs.overseaprojectinformationsystem.config.realm.UserRealm;
import com.cug.cs.overseaprojectinformationsystem.util.JwtFilter;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    @Autowired
    private UserRealm userRealm;

    @Autowired
    private AdminRealm adminRealm;

    @Autowired
    private SuperAdminRealm superAdminRealm;

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        //创建拦截链实例
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置组登录请求，其他路径一律自动跳转到这里
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        //设置拦截链map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //放行请求
        filterChainDefinitionMap.put("/auth/**", "anon");
        filterChainDefinitionMap.put("/public/**", "anon");
/*        //管理员接口（需要admin角色）
        filterChainDefinitionMap.put("/admin/**", "jwt, roles[admin]");
        //超级管理员接口（需要super-admin角色）
        filterChainDefinitionMap.put("/superadmin/**", "jwt, roles[super-admin]");
        //普通用户接口（需要user角色）
        filterChainDefinitionMap.put("/user/**", "jwt, roles[user]");*/
        // 管理员接口（需要admin角色）
        filterChainDefinitionMap.put("/admin/**", "jwt, roles[admin]");

        // 超级管理员接口（需要super-admin角色）
        filterChainDefinitionMap.put("/superadmin/**", "jwt, roles[superadmin]");

        // 需要user:show权限的接口
        filterChainDefinitionMap.put("/test/user", "jwt, perms[user:show]");
//
//        // 需要user角色的其他接口
//        filterChainDefinitionMap.put("/user/**", "jwt, roles[user]");

        //拦截剩下的其他请求
        filterChainDefinitionMap.put("/**", "jwt");
        //设置拦截规则给shiro的拦截链工厂
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 添加自己的自定义拦截器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //拦截链配置，从上向下顺序执行，一般将jwt过滤器放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        //配置拦截链到过滤器工厂
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //返回实例
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器（SecurityManager）配置
     *
     * @return {@link DefaultWebSecurityManager} 默认Web安全管理器实例
     */
    @Bean
public SecurityManager securityManager(
    @Qualifier("userRealm") UserRealm userRealm,
    @Qualifier("adminRealm") AdminRealm adminRealm,
    @Qualifier("superAdminRealm") SuperAdminRealm superAdminRealm) {
    
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    
    // 设置多Realm并指定认证策略（通常使用AtLeastOneSuccessful策略）
    securityManager.setAuthenticator(new ModularRealmAuthenticator());
    securityManager.setRealms(Arrays.asList(userRealm, adminRealm, superAdminRealm));
    
    return securityManager;
}

    /**
     * 开启 Shiro 的注解支持（如 @RequiresRoles, @RequiresPermissions）
     *
     * @return {@link DefaultAdvisorAutoProxyCreator} 自动代理创建器
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

        // 设置代理目标类为 true，表示启用 CGLIB 动态代理，而不是基于接口的代理
        advisorAutoProxyCreator.setProxyTargetClass(true);

        return advisorAutoProxyCreator;
    }

    /**
     * 开启 AOP 注解支持，用于权限控制
     *
     * @param securityManager {@link SecurityManager} 安全管理器实例
     * @return {@link AuthorizationAttributeSourceAdvisor} 权限属性源顾问
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        // 创建权限属性源顾问
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        // 将 SecurityManager 配置到顾问中，确保能够进行权限验证
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        // 返回顾问实例
        return authorizationAttributeSourceAdvisor;
    }
}
