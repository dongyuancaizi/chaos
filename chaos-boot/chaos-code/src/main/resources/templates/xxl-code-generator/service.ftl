package ${package.Service};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.${package.ModuleName}.data.${entity}CreateData;
import com.cui.tech.${package.ModuleName}.data.${entity}ListQueryData;
import com.cui.tech.${package.ModuleName}.data.${entity}QueryData;
import com.cui.tech.${package.ModuleName}.data.${entity}UpdateData;
import com.cui.tech.${package.ModuleName}.data.${entity}Result;
import ListResult;
import PageQueryDto;
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

    int insertModel(${entity}CreateData data);

    int updateModelByUnique(${entity}UpdateData data);

    ${entity}Result selectResultByUnique(${entity}QueryData data);

    List<${entity}Result> selectResults(${entity}ListQueryData data);

    ListResult<${entity}Result> selectResultsByPage(PageQueryDto<${entity}ListQueryData> pageData);
}
