package ${package.Entity};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.tech.chaos.model.db.MuModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${table.name}")
@ApiModel(value="${entity}实体", description="${table.comment!}")
public class ${entity} extends MuModel {

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#if field.name !="mu"&& field.name !="create_time"
          && field.name !="modify_time" && field.name !="is_delete"
          && field.name !="version" && field.name !="id">
    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    @ApiModelProperty(value = "${field.comment}")
    </#if>
    @TableField("${field.name}")
    private ${field.propertyType} ${field.propertyName};

    </#if>
    </#list>
}
