package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.mapper.OtherPerformanceMapper;
import org.springblade.modules.user.service.OtherPerformanceService;

/**
 * @Author 元杰
 * @Date 2022/8/26 18:51
 */

@Service
public class OtherPerformanceServiceImpl extends ServiceImpl<OtherPerformanceMapper, OtherPerformance> implements OtherPerformanceService {

	@Override
	public IPage<OtherPerformance> selectOtherPerformancePage(IPage<Object> page, String otherPerformanceName) {
		IPage<OtherPerformance> pages = baseMapper.selectOtherPerformancePage(page,otherPerformanceName);
		return pages;
	}
}

