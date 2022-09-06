package org.springblade.modules.user.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.service.OpKpiService;
import org.springblade.modules.user.entity.Job;
import org.springblade.modules.user.entity.JobOtherP;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.service.JobOtherPService;
import org.springblade.modules.user.service.JobService;
import org.springblade.modules.user.service.OtherPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/26 18:51
 */
@RestController
@RequestMapping("api/set/op")
@Api(tags = "其他绩效管理")
public class OPController {

	@Autowired
	private OtherPerformanceService otherPerformanceService;
	@Autowired
	private JobOtherPService jobOtherPService;
	@Autowired
	private OpKpiService opKpiService;

	/**
	 *
	 */
	@GetMapping("/selectOtherPerformancePage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入UserDto")
	public R<IPage<OtherPerformance>> selectOtherPerformancePage(Query page, String otherPerformanceName) {
		IPage<OtherPerformance> pages = otherPerformanceService.selectOtherPerformancePage(Condition.getPage(page), otherPerformanceName);
		return R.data(pages);
	}

	/**
	 * 学历字典表 add
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiOperationSupport(order = 2)
	public R add(@RequestBody OtherPerformance param) {
		OtherPerformance em = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OTHER_PERFORMANCE_NAME, param.getOtherPerformanceName()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		OtherPerformance count = otherPerformanceService
			.query()
			.orderByDesc(OtherPerformance.COL_OP_ID)
			.list().get(0);
		param.setOpId(count.getOpId()+1);
		if(param.getOpType().equals(1)){
			param.setOpBtName(param.getOtherPerformanceName()+"(元)");
		}else {
			param.setOpBtName(param.getOtherPerformanceName()+"(个)");
		}
		otherPerformanceService.save(param);
		OpKpi opKpi = new OpKpi();
		opKpi.setKopId("demo");
		opKpi.setOpId(count.getOpId()+1);
		opKpiService.save(opKpi);
		return R.success("添加成功");
	}

	/**
	 * 学历字典表 update
	 *
	 * @param param
	 * @return
	 */

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody OtherPerformance param) {
		otherPerformanceService.updateById(param);
		return R.success("操作成功");
	}

	/**
	 * 批量删除
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除")
	@ApiOperationSupport(order = 4)
	public R delete(@RequestBody List<Integer> param) {
		for (Integer ids : param) {
			JobOtherP em = jobOtherPService.getOne(new QueryWrapper<JobOtherP>().eq(JobOtherP.COL_J_ID,ids));
			if (ObjectUtil.isNotNull(em)) {
				return R.fail("该绩效下存在岗位id为"+em.getJId()+"在使用");
			}
		}
		return R.data(otherPerformanceService.removeByIds(param));
	}
}
