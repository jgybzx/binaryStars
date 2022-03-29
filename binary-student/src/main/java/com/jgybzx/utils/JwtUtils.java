package com.jgybzx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * des: Jwt工具类
 *
 * @author jgybzx
 * @date 2021/3/25 10:28
 */
@Component()
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    private static String key;
    private static long failureTime;

    /**
     * @param subject jet主体
     * @param claims  创建payload的私有声明(根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的)
     * @return
     */
    public static String createJwt(String subject, Map<String, Object> claims) {

        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        //为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey);

        long expMillis = nowMillis + failureTime;
        Date exp = new Date(expMillis);
        //设置过期时间
        builder.setExpiration(exp);
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        //本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(key);
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public static void main(String[] args) {
        Random random = new Random();
        int randomInt = random.nextInt(1000000);
        logger.info("randomInt ={}", randomInt);
        Map<String, Object> claimsMap = new HashMap<>(16);
        claimsMap.put("id", "1");
        claimsMap.put("name", "jgybzxzxz");
        String token = JwtUtils.createJwt("17501696526", claimsMap);
        logger.info("token ={}", token);
        Claims claims = JwtUtils.parseJwt(token);
        logger.info("claims.get(\"id\") = {}", claims.get("id"));
        logger.info("claims.get(\"name\") = {}", claims.get("name"));
        logger.info("claims.getId() = {}", claims.getId());
        logger.info("claims.getSubject() = {}", claims.getSubject());

    }


}
