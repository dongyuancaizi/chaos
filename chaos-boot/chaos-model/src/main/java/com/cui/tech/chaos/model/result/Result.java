package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.DTO;
import com.cui.tech.chaos.model.login.LoginUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基础结果对象", description = "")
public class Result extends DTO {
    /**
     * 响应编码
     */
    @ApiModelProperty(value = "响应编码", example = "200")
    private String code;

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "响应消息", example = "ok")
    private String msg;

    public Result() {
    }

    public Result(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    /**
     * 失败
     *
     * @return
     */
    public void failure() {
        this.setCode(ResultEnum.FAILURE.getCode());
        this.setMsg(ResultEnum.FAILURE.getDefaultMsg());
    }

    public void failure(String result, String message) {
        this.setCode(result);
        this.setMsg(message);
    }

    public void unknow() {
        this.setCode(ResultEnum.UNKONW_ERROR.getCode());
        this.setMsg(ResultEnum.UNKONW_ERROR.getDefaultMsg());
    }

    /**
     * 成功，返回数据
     *
     * @return
     */
    public void success() {
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getDefaultMsg());
    }

}
