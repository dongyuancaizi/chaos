package com.cui.tech.boot.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.boot.demo.api.data.VersionData;
import com.cui.tech.boot.demo.api.data.VersionUpdateData;
import com.cui.tech.boot.demo.api.entity.Version;
import com.cui.tech.boot.demo.api.service.IVersionManageService;
import com.cui.tech.boot.demo.service.mapper.VersionManageMapper;
import com.cui.tech.chaos.model.MU;
import com.cui.tech.chaos.model.PageList;
import com.cui.tech.chaos.model.PageQueryDto;
import com.cui.tech.chaos.model.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 版本管理表 服务实现类
 * </p>
 *
 * @author J.C
 * @since 2020-01-10
 */
@Service
@Component
public class VersionManageServiceImpl extends ServiceImpl<VersionManageMapper, Version> implements IVersionManageService {

    @Override
    public MU insertModel(VersionData data) {
        Version entity = new Version();
        BeanUtils.copyProperties(data, entity);
        boolean flag = save(entity);
        if (flag) {
            return new MU(entity.getMu());
        }
        return null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(VersionUpdateData data) {
        Version entity = new Version();
        BeanUtils.copyProperties(data, entity);
        return update(entity,
                new UpdateWrapper<Version>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public Version selectByMU(MU data) {
        return getOne(new QueryWrapper<Version>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<Version> selectByData(VersionData data) {
        QueryWrapper<Version> query = new QueryWrapper<Version>();
        //query.eq(!data.getPassword().isEmpty(),"password", data.getPassword());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<Version> selectByPage(PageQueryDto<VersionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        //query.eq(!data.getPassword().isEmpty(),"phone", pageData.getData().getPhone());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }


}
