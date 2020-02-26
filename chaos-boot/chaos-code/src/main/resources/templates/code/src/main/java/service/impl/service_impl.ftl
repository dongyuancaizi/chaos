package ${package.ServiceImpl};

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Data}.${entity}Data;
import ${package.Entity}.${entity};
import ${package.Service}.${entity}Service;
import com.cui.tech.boot.demo.service.mapper.VersionManageMapper;
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
@Transactional(rollbackFor = Exception.class)
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {
	@Autowired
	private ${entity}DtoToModel dtoToModel;
	@Autowired
	private ${entity}ModelToDto modelToDto;

	@Override
	public int insertModel(${entity}CreateData data) {
		return baseMapper.insert(dtoToModel.convert(data));
	}

	@Override
	public int updateModelByUnique(${entity}UpdateData data) {
		return baseMapper.update(dtoToModel.convert(data),
				new UpdateWrapper<${entity}>()
						.eq("m_unique", data.getUnique())
		);
	}

	@Override
	public ${entity}Result selectResultByUnique(${entity}QueryData data) {
		return modelToDto.convert(baseMapper.selectOne(new QueryWrapper<${entity}>()
				.eq("m_unique", data.getUnique())));
	}

	@Override
	public List<${entity}Result> selectResults(${entity}ListQueryData data) {
		QueryWrapper<${entity}> query = new QueryWrapper<${entity}>();
		//if (!data.getPassword().isEmpty())
			//query.eq("password", data.getPassword());
		return modelToDto.convert(baseMapper.selectList(query));
	}

	@Override
	public ListResult<${entity}Result> selectResultsByPage(PageQueryDto<${entity}ListQueryData> pageData) {
		Page page = new Page(pageData.getPageNum(), pageData.getPageSize());
		QueryWrapper query=new QueryWrapper();
		//query.eq("phone", pageData.getData().getPhone());
		IPage<${entity}> pageResult = baseMapper.selectPage(page, query);
		return modelToDto.convert(pageResult);
	}
}
