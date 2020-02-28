package ${cfg.p}.manager.controller;

import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
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
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${entity}MnController")
@RestController
@RequestMapping("/manage/${table.name}")
public class ${entity}MnController extends ${superControllerClass} {

    @Autowired
    private I${entity}Service i${entity}Service;

    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ${entity}Data data, BindingResult bindingResult) throws Exception {
        return getResult(bindingResult, i${entity}Service.insertModel(data));
    }

    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<${entity}> one( @RequestBody MU data) throws Exception {
        return getResult(i${entity}Service.selectByMU(data));
    }

    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<${entity}Data> data, BindingResult bindingResult) throws Exception {
        return getResult(bindingResult, i${entity}Service.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<${entity}>> list( @RequestBody ${entity}Data data) throws Exception {
        return getResult(i${entity}Service.selectByData(data));
    }

    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<${entity}> page( @RequestBody PageQueryDto<${entity}Data> data) throws Exception {
        return getResult(i${entity}Service.selectByPage(data));
    }

}
