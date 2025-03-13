import org.apache.shiro.web.filter.AccessControlFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends AccessControlFilter {
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false; // 永远返回false，通过onAccessDenied处理
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");
        
        if (token == null || !token.startsWith("Bearer ")) {
            // 处理未携带token的情况
            return false;
        }
        
        String jwt = token.substring(7);
        JwtToken jwtToken = new JwtToken(jwt);
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (Exception e) {
            // 处理token验证失败
            return false;
        }
    }
} 