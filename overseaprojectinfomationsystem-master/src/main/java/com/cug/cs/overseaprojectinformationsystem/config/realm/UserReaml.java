package com.cug.cs.overseaprojectinformationsystem.config.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: TODO  提供信息：授权信息与认证信息（普通用户）
 * @author: ShengHui
 * @date: 2023-07-21  16:29
 */

@Component
public class UserReaml extends AuthorizingRealm {
    /*@Autowired
    MarketUserMapper marketUserMapper;*/
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*String username = (String) token.getPrincipal();
        
        MarketUserExample marketUserExample = new MarketUserExample();
        marketUserExample.createCriteria().andUsernameEqualTo(username);
        List<MarketUser> marketUsers = marketUserMapper.selectByExample(marketUserExample);
        if (marketUsers.size() == 1) {
            // 说明数据库中有与之对应的数据存在
            MarketUser marketUser = marketUsers.get(0);
            
            // 构造认证信息时, 可以放入需要的用户信息, 放入的用户信息, 可以作为 Principals
            // 放入这个信息是为了取出
            // 第二个参数是 Credentials, 是真实的密码, 会和 AuthenticationToken 中的 password 做比较
            return new SimpleAuthenticationInfo(marketUser, marketUser.getPassword(), getName());
        }*/
        return null;
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
