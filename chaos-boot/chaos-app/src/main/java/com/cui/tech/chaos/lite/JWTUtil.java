package com.cui.tech.chaos.lite;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cui.tech.chaos.lite.exception.BusinessException;
import com.cui.tech.chaos.model.login.JwtData;
import com.cui.tech.chaos.model.result.ResultEnum;
import com.cui.tech.chaos.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author J.C
 * @date 2020/1/8 18:41
 */
@Component
public class JWTUtil {
    public String createToken(String user_mu, String username) {
        String secret = "secret";// token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        // 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = DateUtil.addHour(nowDate, 2);// 2小过期
        String token = JWT.create()
                .withHeader(map)// 设置头部信息 Header
                .withClaim("user_mu", user_mu)
                .withIssuer("CHAOS-SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
                .withSubject("this is chaos token")//设置 载荷 签名的主题
                // .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //设置 载荷 生成签名的时间
                .withExpiresAt(expireDate)//设置 载荷 签名过期的时间
                .sign(algorithm);//签名 Signature
        return token;
    }

    public JwtData getJwtData(String token) {
        JwtData jwtData = new JwtData();
        try {
            // 验证 token
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("CHAOS-SERVICE")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                String key = entry.getKey();
                Claim claim = entry.getValue();
                // 获取 token 中的 user_mu
                if (key.equals("user_mu")) {
                    jwtData.setUser_mu(claim.asString());
                }
                if (key.equals("exp")) {
                    jwtData.setExp(claim.asDate());
                }
            }
        } catch (JWTVerificationException e) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "token错误");
        }

        return jwtData;
    }
}
