package com.cui.tech.chaos.model.db;

import com.baomidou.mybatisplus.annotation.*;
import com.cui.tech.chaos.model.DTO;
import lombok.Data;

import java.util.Date;

@Data
public class IdModel extends DTO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "create_time", fill = FieldFill.INSERT, update = "NOW()")
    private Date createTime;
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE, update = "NOW()")
    private Date modifyTime;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_delete", fill = FieldFill.INSERT, update = "0")
    private Integer isDelete;
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE, update = "%s+1")
    private Integer version;

    public IdModel() {
        this.version = 1;
    }
}
