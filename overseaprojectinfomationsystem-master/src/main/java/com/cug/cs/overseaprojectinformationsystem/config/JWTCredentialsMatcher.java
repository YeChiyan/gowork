import com.cug.cs.overseaprojectinformationsystem.config.JwtToken;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import com.cug.cs.overseaprojectinformationsystem.entity.UserModel;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTCredentialsMatcher implements CredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(JWTCredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = ((JwtToken) authenticationToken).getCredentials().toString();
        log.info("JWTCredentialsMatcher token: {}", token);
        
        UserModel userModel = (UserModel) authenticationInfo.getPrincipals().getPrimaryPrincipal();
        log.info("JWTCredentialsMatcher user: {}", userModel.toString());
        
        // 验证token有效性并检查用户信息匹配
        return JwtUtil.verifyToken(token, userModel.getUsername(), userModel.getRole());
    }
} 