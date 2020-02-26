package com.cui.tech.boot.demo.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.boot.demo.api.data.VersionData;
import com.cui.tech.boot.demo.api.entity.Version;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 * <p>
 * 版本管理表 服务类
 * </p>
 *
 * @author J.C
 * @since 2020-01-10
 */
public interface IVersionManageService extends IService<Version> {

    MU insertModel(VersionData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<VersionData> data);

    Version selectByMU(MU data);

    List<Version> selectByData(VersionData data);

    PageList<Version> selectByPage(PageQueryDto<VersionData> pageData);

}
