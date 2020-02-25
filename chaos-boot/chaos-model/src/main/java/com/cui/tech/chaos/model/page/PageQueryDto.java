package com.cui.tech.chaos.model.page;

import com.cui.tech.chaos.model.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页对象", description = "")
public class PageQueryDto<T> {
    @ApiModelProperty(value = "第几页", example = "1")
    private Integer pageNum = Constants.DEFAULT_PAGE;
    @ApiModelProperty(value = "总条数", example = "10")
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "结果对象")
    private T data;
}
