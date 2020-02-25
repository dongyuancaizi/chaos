package com.cui.tech.boot.demo.api.data;

import com.cui.tech.chaos.model.MU;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "VersionUpdateData", description = "")
public class VersionUpdateData extends MU {
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionNumber;

    /**
     * 版本描述
     */
    @ApiModelProperty(value = "版本描述")
    private String versionDescription;

    /**
     * 版本类型，1 强更， 2 非强更
     */
    @ApiModelProperty(value = "版本类型，1 强更， 2 非强更")
    private Integer versionType;

    /**
     * 客户端类型，1 Android， 2 ios
     */
    @ApiModelProperty(value = "客户端类型，1 Android， 2 ios")
    private Integer clientType;

    /**
     * 1当前线上版本（最新版本） 2新建 3下线
     */
    @ApiModelProperty(value = "1当前线上版本（最新版本） 2新建 3下线")
    private Integer versionState;

}
