package mytest.shirodemo.realm;

import lombok.extern.slf4j.Slf4j;
import mytest.shirodemo.entity.Account;
import mytest.shirodemo.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

//@Slf4j
public class AccountRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(AccountRealm.class);
    @Autowired
    private AccountService accountService;
    //权限
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户的信息
        Subject subject = SecurityUtils.getSubject();
        Account account =(Account) subject.getPrincipals().getPrimaryPrincipal();

        //设置角色
        Set<String> roles=new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
//        info.setStringPermissions(account.getPerms());
        info.addStringPermission(account.getPerms());
        return info;
    }
    //角色信息
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;//把已经有的 token 强转
        Account account = accountService.findByUsername(token.getUsername());
        if(account==null){
//            System.out.println();
//            log.info("用户不存在");
            System.out.println("用户不存在");
            return null;
        }
        System.out.println(account);
        //这是把一整个对象都存储进去了
        return new SimpleAuthenticationInfo(account,account.getPassword(),getName());//相当于Shiro 在进行密码验证
//        return null;
    }


}
