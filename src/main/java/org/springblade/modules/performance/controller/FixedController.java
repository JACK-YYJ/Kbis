package org.springblade.modules.performance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.SetFactorDto;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.service.KpiFixedService;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.service.JobCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/8 14:34
 */
@RestController
@RequestMapping("api/kpi/fixed")
@Api(tags = "固定绩效考核分值")
@CrossOrigin
public class FixedController {
    @Autowired
	private KpiFixedService kpiFixedService;

	@Autowired
	private JobCertificateService jobCertificateService;

	@GetMapping("/selectfixedPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入toMonth")
	public R selectfixedPage(Query page, String toMonth, String idOrName) {
		if (toMonth==null){
			return R.fail("请从新输入月份");
		}
		IPage<KpiFixed> pages = kpiFixedService.selectfixedPage(Condition.getPage(page), toMonth,idOrName);
		return R.data(pages);
	}

	/**
	 * 设置系数
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "设置系数")
	@ApiOperationSupport(order = 2)
	public R update(@RequestBody SetFactorDto param) {
		List<JobCertificate> list = jobCertificateService.query().list();
		list.forEach(s-> {
			s.setJobCertificateFactor(param.getJobCertificateFactor());
			s.setAgeFactor(param.getAgeFactor());
		});
		jobCertificateService.updateBatchById(list);
		return R.success("操作成功");
	}

	/**
	 * 编辑
	 * @param param
	 * @return
	 */
	@PostMapping("/updateOneKpiFixed")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R updateOneKpiFixed(@RequestBody KpiFixed param) {
		kpiFixedService.updateByOne(param);
		return R.success("操作成功");
	}

	/**
	 * 导入数据
	 * @param kpiFixedList
	 * @return
	 */
	@PostMapping("/updateKpiFixedList")
	@ApiOperation(value = "保存")
	@ApiOperationSupport(order = 4)
	public R updateKpiFixedList(@RequestBody List<KpiFixed> kpiFixedList) {
		kpiFixedService.updateByList(kpiFixedList);
		return R.success("操作成功");
	}
}
