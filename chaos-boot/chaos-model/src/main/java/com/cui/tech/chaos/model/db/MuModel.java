package com.cui.tech.chaos.model.db;

import com.baomidou.mybatisplus.annotation.*;
import com.cui.tech.chaos.model.DTO;
import lombok.Data;

import java.util.Date;

@Data
public class MuModel extends DTO {
    private Integer id;
    @TableId(value = "mu", type = IdType.UUID)
    private String mu;
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

}
