package ${package.Data};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="${entity}Data", description="${table.comment!}")
public class ${entity}Data {

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
