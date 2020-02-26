package com.cui.tech.chaos.model.db;


import lombok.Data;

/**
 * @author J.C
 * @date 2020/2/26 15:56
 */
@Data
public class UpdateData<T extends DATA> extends MU {
    private T data;
}
