package yb.demo.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * @Auther: Yang
 * @Date: 2019/12/01 14:33
 * @Description:
 */
public class JwtUtils {
    private static String SECRET = "mySecret";
    private static Long EXPIRATION_REMEMBER = 60L * 1000;
    private static Long REFRESH_EXPIRATION_REMEMBER = 60*60*1000L;

    /***
     * 生成token
     * @param userName
     * @return
     */
    public static String createToken(String userName,int type) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put("userName", userName);
        long expiration = type == 1 ? EXPIRATION_REMEMBER : REFRESH_EXPIRATION_REMEMBER;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)//设置签名算法和签名密码
                .setClaims(claim)//设置自定义数据
                .setIssuer("yang")//设置签发人
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//设置过期时间,如果type=1，则是普通的token,否则是refresh_token
                .compact();
    }

//    /***
//     * 通过token先获取claims然后从claims中获取openid
//     * @param token
//     * @return
//     */
//    public static String getOpenId(String token) {
//        Claims claims = getClaimsFromToken(token);
//        if(claims != null){
//            String openId = (String) claims.get("openId");  //try catch
//            return openId;
//        }
//        return "";
//    }

    /***
     * 通过secret获取token中的claim
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims == null ? null : claims;
    }


    /***
     * 验证token是否过期
     * @param token
     * @return
     */
    public static Boolean validateToken(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }
}
