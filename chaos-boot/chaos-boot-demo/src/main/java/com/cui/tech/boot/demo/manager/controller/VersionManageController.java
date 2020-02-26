package com.cui.tech.boot.demo.manager.controller;

import com.cui.tech.boot.demo.api.data.VersionData;
import com.cui.tech.boot.demo.api.entity.Version;
import com.cui.tech.boot.demo.api.service.IVersionManageService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 版本管理表 前端控制器
 * </p>
 *
 * @author J.C
 * @since 2020-01-10
 */
@Api(tags = "VersionManageController")
@RestController
@RequestMapping("/manage/versionManage")
public class VersionManageController extends BaseController {

    @Autowired
    private IVersionManageService iVersionManageService;

    @PostMapping("/add")
    @ApiOperation(value = "版本管理模块-创建版本管理", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated VersionData data, BindingResult bindingResult) throws Exception {
        return getResult(bindingResult, iVersionManageService.insertModel(data));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除版本管理", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(
            @RequestBody MU data) throws Exception {
        return getResult(iVersionManageService.deleteModel(data));
    }

    @PostMapping("/one")
    @ApiOperation(value = "查询版本管理", notes = "", httpMethod = "POST")
    public DataResult<Version> one(
            @RequestBody MU data) throws Exception {
        return getResult(iVersionManageService.selectByMU(data));
    }

    @PostMapping("/update")
    @ApiOperation(value = "版本管理-编辑版本", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(
            @ApiParam(value = "更新版本管理") @RequestBody @Validated UpdateData<VersionData> data, BindingResult bindingResult) throws Exception {
        return getResult(bindingResult, iVersionManageService.updateModelByMU(data));
    }


    @PostMapping("/list")
    @ApiOperation(value = "版本管理模块-查询版本管理列表", notes = "", httpMethod = "POST")
    public DataResult<List<Version>> list(
            @RequestBody VersionData data) throws Exception {
        return getResult(iVersionManageService.selectByData(data));
    }

    @PostMapping("/page")
    @ApiOperation(value = "版本管理模块-查询版本管理列表", notes = "", httpMethod = "POST")
    public PageResult<Version> page(
            @RequestBody PageQueryDto<VersionData> data) throws Exception {
        return getResult(iVersionManageService.selectByPage(data));
    }


}
