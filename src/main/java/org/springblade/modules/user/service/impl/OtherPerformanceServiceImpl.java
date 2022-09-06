package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;
import org.springblade.modules.performance.service.OpKpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
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

	@Autowired
	private KpiOtherPerformanceService kpiOtherPerformanceService;
	@Autowired
	private OpKpiService opKpiService;

	@Override
	public IPage<OtherPerformance> selectOtherPerformancePage(IPage<Object> page, String otherPerformanceName) {
		IPage<OtherPerformance> pages = baseMapper.selectOtherPerformancePage(page,otherPerformanceName);
		return pages;
	}

	@Override
	public HashMap<String, String> selectBt() {
		KpiOtherPerformance kpiOtherPerformance = kpiOtherPerformanceService.getById(1);
		List<OpKpi> lambdaQueryOpKpi = opKpiService.lambdaQuery()
			.eq(OpKpi::getKopId,kpiOtherPerformance.getKopId())
			.list();

		HashMap<String, String> btVOmap = new HashMap<>();
		for (int i = 0; i < lambdaQueryOpKpi.size(); i++) {
			OtherPerformance byId = this.getById(lambdaQueryOpKpi.get(i).getOpId());
			btVOmap.put("Other"+(i+1),byId.getOpBtName());
		}
		return btVOmap;
	}
}

