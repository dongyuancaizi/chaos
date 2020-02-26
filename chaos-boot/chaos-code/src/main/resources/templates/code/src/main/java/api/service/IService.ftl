package ${package.Service};

import com.baomidou.mybatisplus.extension.service.IService;
import ${package.Data}.${entity}Data;
import ${package.Entity}.${entity};
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
*/
public interface ${table.serviceName} extends IService<${entity}> {

    MU insertModel(${entity}Data data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<${entity}Data> data);

    Version selectByMU(MU data);

    List<${entity}> selectByData(VersionData data);

    PageList<${entity}> selectByPage(PageQueryDto<${entity}Data> pageData);
}
