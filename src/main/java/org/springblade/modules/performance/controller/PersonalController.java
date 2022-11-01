package org.springblade.modules.performance.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiPersonal;
import org.springblade.modules.performance.service.KpiPersonalService;
import org.springblade.modules.performance.vo.PercentageSelectVo;
import org.springblade.modules.performance.vo.StatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/15 16:09
 */
@RestController
@RequestMapping("api/kpi/Personal")
@Api(tags = "个人绩效核算")
@CrossOrigin
public class PersonalController {
	@Autowired
	private KpiPersonalService kpiPersonalService;
	/**
	 *查询
	 */
	@GetMapping("/selectPersonalPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询", notes = "传入toMonth ")
	public R<IPage<PercentageSelectVo>> selectPersonalPage(Query page, String toMonth, String idOrName) {
//		if (toMonth==null){
//			return R.fail("请从新输入月份");
//		}
		IPage<PercentageSelectVo> pages = kpiPersonalService.selectPersonalPage(Condition.getPage(page), toMonth,idOrName);
		return R.data(pages);
	}

	@GetMapping("/statistics")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "统计", notes = "传入toMonth")
	public R selectPersonalPage(String toMonth) {
		List<StatisticsVo> kpiPersonalIPage = kpiPersonalService.selectStatisticsList(toMonth);
		return R.data(kpiPersonalIPage);
	}

}
