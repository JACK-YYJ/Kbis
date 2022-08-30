package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.user.vo.UserVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.Degree;
import org.springblade.modules.user.mapper.DegreeMapper;
import org.springblade.modules.user.service.DegreeService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class DegreeServiceImpl extends ServiceImpl<DegreeMapper, Degree> implements DegreeService {

	@Override
	public IPage<Degree> selectDegreePage(IPage<Object> page, String idOrName) {
		IPage<Degree> pages = baseMapper.selectDegreePage(page,idOrName);
		return pages;
	}
}

