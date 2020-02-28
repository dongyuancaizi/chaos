package ${cfg.p}.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${cfg.p}.api.entity.${entity};
import ${cfg.p}.api.data.${entity}Data;
import ${cfg.p}.api.service.I${entity}Service;
import ${cfg.p}.service.mapper.${entity}Mapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Service
@Component
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {

	@Override
	public MU insertModel(${entity}Data data) {
		${entity} entity = new ${entity}();
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
	public boolean updateModelByMU(UpdateData<${entity}Data> data) {
		${entity} entity = new ${entity}();
		BeanUtils.copyProperties(data.getData(), entity);
		return update(entity,
				new UpdateWrapper<${entity}>()
						.eq(Table.MU, data.getMu())
		);
	}

	@Override
	public ${entity} selectByMU(MU data) {
		return getOne(new QueryWrapper<${entity}>()
				.eq(Table.MU, data.getMu()));
	}

	@Override
	public List<${entity}> selectByData(${entity}Data data) {
		QueryWrapper<${entity}> query = new QueryWrapper<${entity}>();
		//query.eq(!data.getPassword().isEmpty(),"password", data.getPassword());
		query.orderByDesc(Table.ID);
		return list(query);
	}

	@Override
	public PageList<${entity}> selectByPage(PageQueryDto<${entity}Data> pageData) {
		QueryWrapper query = new QueryWrapper();
		//query.eq(!data.getPassword().isEmpty(),"phone", pageData.getData().getPhone());
		query.orderByDesc(Table.ID);
		return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
	}

}
