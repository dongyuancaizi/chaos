package com.cui.tech.chaos.model.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cui.tech.chaos.model.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "列表结果对象", description = "")
public class PageList<T> extends DTO {
    @ApiModelProperty(value = "列表")
    private List<T> list;
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "当前页")
    private Long current;

    public PageList() {
    }

    public PageList(IPage<T> page) {
        this.setCurrent(page.getCurrent());
        this.setTotal(page.getTotal());
        this.setList(page.getRecords());
    }

}
