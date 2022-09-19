package org.springblade.modules.performance.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.SetFactorDto;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.service.KpiWorkloadService;
import org.springblade.modules.user.entity.JobCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/9 16:43
 */
@RestController
@RequestMapping("api/kpi/work")
@Api(tags = "工作量绩效分值")
@CrossOrigin
public class WorkloadController {
	@Autowired
	private KpiWorkloadService kpiWorkloadService;

	@GetMapping("/selectWorkloadPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入toMonth")
	public R<IPage<KpiWorkload>> selectWorkloadPage(Query page, String toMonth, String idOrName) {
		if (toMonth==null){
			return R.fail("请从新输入月份");
		}
		IPage<KpiWorkload> pages = kpiWorkloadService.selectWorkloadPage(Condition.getPage(page), toMonth,idOrName);
		return R.data(pages);
	}

	/**
	 * 编辑
	 * @param param
	 * @return
	 */
	@PostMapping("/updateOneKpiWork")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R updateOneKpiFixed(@RequestBody KpiWorkload param) {
		return kpiWorkloadService.updateByOne(param);
	}
	/**
	 * 导入数据
	 * @param kpiFixedList
	 * @return
	 */
	@PostMapping("/updateKpiWorkList")
	@ApiOperation(value = "保存")
	@ApiOperationSupport(order = 4)
	public R updateKpiWorkList(@RequestBody List<KpiWorkload> kpiFixedList) {
		return kpiWorkloadService.updateByList(kpiFixedList);
	}
}
