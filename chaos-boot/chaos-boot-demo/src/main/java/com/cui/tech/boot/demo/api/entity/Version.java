package com.cui.tech.boot.demo.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.MuModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 版本管理表
 * </p>
 *
 * @author J.C
 * @since 2020-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chaos_version")
public class Version extends MuModel {

    @TableField("version_number")
    private String versionNumber;

    @TableField("version_description")
    private String versionDescription;

    /**
     * 版本类型，1 强更， 2 非强更
     */
    @TableField("version_type")
    private Integer versionType;

    /**
     * 客户端类型，1 Android， 2 ios
     */
    @TableField("client_type")
    private Integer clientType;

    /**
     * 1当前线上版本（最新版本） 2新建 3下线
     */
    @TableField("version_state")
    private Integer versionState;

}
