package org.springblade.modules.performance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;
import org.springblade.modules.performance.service.OpKpiService;
import org.springblade.modules.performance.vo.KpiAttendanceDataVo;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.KpiBtVO;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.service.OtherPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/3 12:54
 */
@RestController
@RequestMapping("api/kpi/opattendance")
@Api(tags = "其它绩效")
public class OpKpiController {

	@Autowired
	private OtherPerformanceService otherPerformanceService;
	@Autowired
	private KpiOtherPerformanceService kpiOtherPerformanceService;
	@Autowired
	private OpKpiService opKpiService;


	/**
	 *出勤率
	 */
	@GetMapping("/selectbt")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询表头", notes = "传入toMonth")
	public R selectAttendancePage() {
		KpiOtherPerformance kpiOtherPerformance = kpiOtherPerformanceService.getById(1);
		HashMap<String, String> btVOmap = otherPerformanceService.selectBt();
//		lambdaQueryOpKpi.forEach(s->{
//			OtherPerformance byId = otherPerformanceService.getById(s.getOpId());
//			btVOmap.put(byId.getOpId(),byId.getOtherPerformanceName());
//		});
		KpiAttendanceDataVo kpiAttendanceDataVo = new KpiAttendanceDataVo();
		kpiAttendanceDataVo.setKpiOpId(kpiOtherPerformance.getKpiOpId());
		kpiAttendanceDataVo.setUserCode(kpiOtherPerformance.getUserCode());
		kpiAttendanceDataVo.setUserName(kpiOtherPerformance.getUserName());
		kpiAttendanceDataVo.setAttendanceMonth(kpiOtherPerformance.getAttendanceMonth());
		kpiAttendanceDataVo.setKpiOpList(Collections.singletonList(btVOmap));
		return R.data(kpiAttendanceDataVo);
	}
	/**
	 *出勤率
	 */
	@GetMapping("/selectKpiAttendancePage")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "条件分页查询", notes = "传入toMonth")
	public R<IPage<KpiAttendanceDataVo>> selectKpiAttendancePage(Query page,String toMonth,String idOrName) {
		if (toMonth==null){
			return R.fail("请从新输入月份");
		}
		IPage<KpiAttendanceDataVo> pages = kpiOtherPerformanceService.seleckpitAttendancePage(Condition.getPage(page),toMonth,idOrName);
		return R.data(pages);
	}
}
