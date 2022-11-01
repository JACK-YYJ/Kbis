package org.springblade.modules.performance.controller;


import cn.hutool.core.util.ObjectUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.AccountingDto;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.mapper.KpiAccountingMapper;
import org.springblade.modules.performance.service.KpiAccountingService;
import org.springblade.modules.performance.service.KpiPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/14 14:37
 */
@RestController
@RequestMapping("api/kpi/accounting")
@Api(tags = "绩效每分值核算")
@CrossOrigin
public class AccountingController {


	@Autowired
	private KpiAccountingService kpiAccountingService;
	@Autowired
	private KpiPersonalService kpiPersonalService;
	@Autowired
	private KpiAccountingMapper kpiAccountingMapper;

	/**
	 *查询
	 */
	@GetMapping("/selectAccountingPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询", notes = "传入toMonth")
	public R selectAccountingPage(String toMonth) {
//		if (toMonth==null){
//			return R.fail("请从新输入月份");
//		}
		return kpiAccountingService.selectAccountingPage(toMonth);
	}

	@PostMapping("/Save")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "保存", notes = "")
	public R Save(@RequestBody AccountingDto param) {
		if (
			ObjectUtil.isEmpty(param.getPerformanceSum())&
			ObjectUtil.isEmpty(param.getPhyCentum())&
			ObjectUtil.isEmpty(param.getMedFixedCoefficient())&
			ObjectUtil.isEmpty(param.getPhyFixedCoefficient())
		){
			return R.fail("请重新输入");
		}

//		kpiPersonalService.deleteBysaveAccounting(param.getToMonth());
		R saves = kpiAccountingService.saves(param);
		kpiPersonalService.updateByPersonal(param.getToMonth());
		return R.success(saves.getMsg());

	}



	@PostMapping("/Savetj")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "统计保存", notes = "")
	public R savetj(@RequestBody AccountingDto param) {
		if (ObjectUtil.isEmpty(param.getPerformanceSum())&
			ObjectUtil.isEmpty(param.getPhyCentum())&
			ObjectUtil.isEmpty(param.getMedFixedCoefficient())&
			ObjectUtil.isEmpty(param.getPhyFixedCoefficient())){
			return R.fail("请从新输入");
		}
//		kpiPersonalService.deleteBysaveAccounting(param.getToMonth());
		kpiAccountingService.savess(param);
		kpiPersonalService.updateByPersonal(param.getToMonth());
		return R.success("保存成功");
	}
}
