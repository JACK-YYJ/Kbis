package org.springblade.modules.performance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.AccountingDto;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.service.KpiAccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	/**
	 *查询
	 */
	@GetMapping("/selectAccountingPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询", notes = "传入toMonth")
	public R selectAccountingPage(String toMonth) {
		if (toMonth==null){
			return R.fail("请从新输入月份");
		}
		KpiAccounting pages = kpiAccountingService.selectAccountingPage(toMonth);
		return R.data(pages);
	}
	@PostMapping("/Save")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "保存", notes = "")
	public R Save(@RequestBody AccountingDto param) {
		if (param==null){
			return R.fail("请从新输入");
		}

		return kpiAccountingService.saves(param);
	}

}