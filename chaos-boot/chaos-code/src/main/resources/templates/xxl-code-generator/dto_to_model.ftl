package ${package.ServiceImpl};

import com.cui.tech.${package.ModuleName}.data.${entity}CreateData;
import com.cui.tech.${package.ModuleName}.data.${entity}UpdateData;
import ${package.Entity}.${entity};
import org.springframework.stereotype.Service;
import DtoToModel;
import org.springframework.beans.BeanUtils;
/**
* <p>
* ${table.comment!} 模型转换为传输对象
* </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${entity}DtoToModel extends DtoToModel {
	public ${entity} convert(${entity}CreateData data) {
		${entity} entity = new ${entity}();
		BeanUtils.copyProperties(data, entity);
		create(entity);
		return entity;
	}


	public ${entity} convert(${entity}UpdateData data) {
		${entity} entity = new ${entity}();
		BeanUtils.copyProperties(data, entity);
		return entity;
	}
}
