package com.cui.tech.chaos.model.result;

import com.cui.tech.chaos.model.page.PageList;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "列表结果对象", description = "")
public class PageResult<T> extends Result {
    private PageList<T> page;

    public PageResult() {
        success();
    }

}
