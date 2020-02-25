package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.login.LoginUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Result", description = "")
public class DataResult<T> extends Result {

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "结果对象")
    private T data;

    public DataResult() {
        success();
    }


}
