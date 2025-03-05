public class JwtUtils {
    private static final long EXPIRE_TIME = 60 * 60 * 1000; // 1小时过期
    private static final String SECRET = "your_secret_key"; // 加密密钥

    public static String createToken(String username, String role) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username", username)
                .withClaim("role", role)
                .withExpiresAt(date)
                .sign(algorithm);
    }
} 