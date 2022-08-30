package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.mapper.PositionMapper;
import org.springblade.modules.user.entity.Position;
import org.springblade.modules.user.service.PositionService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

	@Override
	public IPage<Position> selectPositionPage(IPage<Object> page, String idOrName) {
		IPage<Position> pages =  baseMapper.selectPositionPage(page,idOrName);
		return pages;
	}
}


