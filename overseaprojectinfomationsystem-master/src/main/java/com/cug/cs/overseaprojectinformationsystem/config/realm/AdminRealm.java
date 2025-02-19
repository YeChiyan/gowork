package com.cug.cs.overseaprojectinformationsystem.config.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 提供信息：授权信息与认证信息（管理员）
 * @author: ShengHui
 * @date: 2023-07-21  16:25
 */

@Component
public class AdminRealm extends AuthorizingRealm {
    /*@Autowired
    MarketAdminMapper marketAdminMapper;
    @Autowired
    MarketRoleMapper marketRoleMapper;
    @Autowired
    MarketPermissionMapper marketPermissionMapper;*/
    /**
     * 身份验证
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author huangshenghui
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*String username = (String) token.getPrincipal();
        // TODO 验证token合法性 如果验证通过，可以从jwt中获取用户信息（用户名，角色）
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        marketAdminExample.createCriteria().andUsernameEqualTo(username);
        List<MarketAdmin> marketAdmins = marketAdminMapper.selectByExample(marketAdminExample);
        if (marketAdmins.size() == 1) {
            // 说明数据库中有与之对应的数据存在
            MarketAdmin marketAdmin = marketAdmins.get(0);
            
            // 构造认证信息时, 可以放入需要的用户信息, 放入的用户信息, 可以作为 Principals
            // 放入这个信息是为了取出
            // 第二个参数是 Credentials, 是真实的密码, 会和 AuthenticationToken 中的 password 做比较
            return new SimpleAuthenticationInfo(marketAdmin, marketAdmin.getPassword(), getName());
        }*/
        return null;
    }
    
    /**
     *  授权验证
     * @param principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author huangshenghui
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 先获得 Principal 信息
       /* MarketAdmin primaryPrincipal = ((MarketAdmin) principals.getPrimaryPrincipal());
        // 根据用户信息查询出对应的权限列表
        // 用 MyBatis 查询用户的 roleIds
        MarketAdminExample marketAdminExample = new MarketAdminExample();
        marketAdminExample.createCriteria().andUsernameEqualTo(primaryPrincipal.getUsername());
        MarketAdmin marketAdmin = marketAdminMapper.selectByExample(marketAdminExample).get(0);
        
        Integer[] roleIds = marketAdmin.getRoleIds();
        // 通过 roleId 查询角色所有的权限
        List<String> permissions = new ArrayList<>();
        for (Integer roleId : roleIds) {
            MarketPermissionExample marketPermissionExample = new MarketPermissionExample();
            marketPermissionExample.createCriteria().andRoleIdEqualTo(roleId);
            List<MarketPermission> marketPermissions = marketPermissionMapper.selectByExample(marketPermissionExample);
            for (MarketPermission marketPermission : marketPermissions) {
                permissions.add(marketPermission.getPermission());
            }
        }*/
        
        // List<String> permissions = Arrays.asList("aaa");
        // List<String> permissions = Arrays.asList(roleIds);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
