package org.springblade.modules.performance.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.OtherPerformanceService;
import org.springblade.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@CrossOrigin
public class OpKpiController {


	@Autowired
	private KpiOtherPerformanceService kpiOtherPerformanceService;
	@Autowired
	private UserService userService;

	@GetMapping("/selectOpattendancePage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入toMonth")
	public R selectOpattendancePage(Query page, String toMonth,String idOrName) {
//		if (toMonth==null){
//			return R.fail("请从新输入月份");
//		}
		IPage<KpiOtherPerformance> pages = kpiOtherPerformanceService.selectOpattendancePage(Condition.getPage(page), toMonth,idOrName);
		return R.data(pages);
	}

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 2)
	public R update(@RequestBody KpiOtherPerformance param) {
		kpiOtherPerformanceService.updateByAllPrice(param);
		return R.success("操作成功");
	}

	/**
	 * 保存
	 * @param paramList
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "保存")
	@ApiOperationSupport(order = 3)
	public R add(@RequestBody List<KpiOtherPerformance> paramList) {
		List<R> rList =new ArrayList<>();
		for (KpiOtherPerformance s : paramList) {
			User one = userService.getOne(new QueryWrapper<User>().eq(User.COL_USER_CODE, s.getUserCode()));
			if(one==null){
				rList.add(R.data(one));
			}
			kpiOtherPerformanceService.updateByAllPrice(s);
		}
		if (ObjectUtil.isAllNotEmpty(rList)){
			return R.fail("校验到Excel不存在的工号");
		}
		return R.success("操作成功");
	}

	/**
	 * 从新计算一下
	 * @return
	 */
	@PostMapping("/compute")
	@ApiOperation(value = "从新计算一下")
	@ApiOperationSupport(order = 4)
	public R compute(@RequestBody String toMonth) {
		List<KpiOtherPerformance> paramList = kpiOtherPerformanceService.selectToMonth(toMonth);
		kpiOtherPerformanceService.updateByAllCompute(paramList);
		return R.success("操作成功");
	}
}
