package ${cfg.p}.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author ${author}
 * @since ${date}
*/
public interface ${table.serviceName} extends IService<${entity}> {

    MU insertModel(${entity}Data data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<${entity}Data> data);

    ${entity} selectByMU(MU data);

    List<${entity}> selectByData(${entity}Data data);

    PageList<${entity}> selectByPage(PageQueryDto<${entity}Data> pageData);
}
