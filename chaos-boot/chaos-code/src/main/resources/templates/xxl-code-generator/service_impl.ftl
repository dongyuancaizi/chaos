package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ListResult;
import PageQueryDto;
import com.cui.tech.demo.data.*;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
