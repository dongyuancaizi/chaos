package com.cui.tech.chaos.model.login;

import lombok.Data;

import java.util.Date;

/**
 * @author J.C
 * @date 2020/2/23 15:00
 */
@Data
public class JwtData {
    String user_mu;
    Date exp;
}
