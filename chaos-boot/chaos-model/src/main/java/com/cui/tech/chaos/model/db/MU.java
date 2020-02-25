package com.cui.tech.chaos.model.db;

import com.cui.tech.chaos.model.DTO;
import lombok.Data;

/**
 * @author Jian.cui
 * @date 2019/11/7 17:30
 */
@Data
public class MU extends DTO {
    private String mu;

    public MU() {
    }

    public MU(String mu) {
        this.mu = mu;
    }
}
