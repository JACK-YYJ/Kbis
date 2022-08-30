package org.springblade.modules.performance.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.UpdateDto;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.mapper.KpiAttendanceMapper;
import org.springblade.modules.performance.service.KpiAttendanceService;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/29 10:53
 */
@RestController
@RequestMapping("api/kpi/attendance")
@Api(tags = "出勤率")
public class AttendanceController {
	@Autowired
	public UserService userService;

	@Autowired
	private KpiAttendanceService kpiAttendanceService;


	/**
	 *出勤率
	 */
	@GetMapping("/selectAttendancePage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入toMonth")
	public R<IPage<KpiAttendanceVo>> selectAttendancePage(Query page, String toMonth) {
		if (toMonth==null){
	 	 return R.fail("请从新输入月份");
		}
		IPage<KpiAttendanceVo> pages = kpiAttendanceService.selectAttendancePage(Condition.getPage(page), toMonth);
		return R.data(pages);
	}

	/**
	 * 出勤率 update
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody KpiAttendance param) {
		kpiAttendanceService.updateById(param);
		return R.success("操作成功");
	}

	/**
	 * 出勤率 update
	 * @param param
	 * @return
	 */
	@PostMapping("/updateDaySum")
	@ApiOperation(value = "设置全勤天数", notes = "\t\"toDaySum\": 25,\n" +
		"\t\"toMonth\": \"2022-08-20\"")
	@ApiOperationSupport(order = 3)
	public R updateDaySum(@RequestBody UpdateDto param) {
		//str 转 date
		Date stf = DateUtil.parse(param.getToMonth());
		List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.selectAttendance(param);
		for (KpiAttendance kpiAttendance : kpiAttendanceList) {
			kpiAttendance.setMonthDay(param.getToDaySum());
			kpiAttendance.setAttendanceDay(param.getToDaySum());
		}
		kpiAttendanceService.updateBatchById(kpiAttendanceList);
		return R.success("操作成功");
	}


}
