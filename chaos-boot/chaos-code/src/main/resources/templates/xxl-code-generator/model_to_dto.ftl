package ${package.ServiceImpl};

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ListResult;
import com.cui.tech.${package.ModuleName}.data.${entity}Result;
import ${package.Entity}.${entity};
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




/**
 * <p>
 * ${table.comment!} 模型转换为传输对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
*/
@Service
public class ${entity}ModelToDto {
	public ${entity}Result convert(${entity} model) {
		${entity}Result result = new ${entity}Result();
		if (model == null) {
			return null;
		}
		BeanUtils.copyProperties(model, result);
		return result;
	}

	public List<${entity}Result> convert(List<${entity}> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<${entity}Result>();
		}
		List<${entity}Result> results = models.stream().map(entity -> convert(entity)).collect(Collectors.toList());
		return results;
	}

	public ListResult<${entity}Result> convert(IPage<${entity}> page) {
			ListResult<${entity}Result> result = new ListResult<${entity}Result>();
			result.setPageNum(page.getCurrent());
			result.setTotal(page.getTotal());
			result.setList(convert(page.getRecords()));
			return result;
	}
}
