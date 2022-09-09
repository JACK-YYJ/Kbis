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
import org.springblade.modules.user.dto.UpdateJobDto;
import org.springblade.modules.user.entity.*;
import org.springblade.modules.user.service.*;
import org.springblade.modules.user.vo.JobOtherPVo;
import org.springblade.modules.user.vo.WorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 元杰
 * @Date 2022/8/25 18:05
 */
@RestController
@RequestMapping("api/set/job")
@Api(tags = "岗位字典表(待处理)")
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private JobWorkService jobWorkService;

	@Autowired
	private JobOtherPService jobOtherPService;
	@Autowired
	public UserService userService;


	@Autowired
	private WorkService workService;

	/**
	 * 岗位字典表分页查询
	 */
	@GetMapping("/selectJobPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "")
	public R<IPage<Job>> selectJobPage(Query page, String jobName) {
		IPage<Job> pages = jobService.selectJobPage(Condition.getPage(page),jobName);
		return R.data(pages);
	}

	/**
	 * 查询
	 */
	@GetMapping("/selectJobWork")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "工作量计分值的查询", notes = "第一次添加会默认 加到关系表里默认值为0")
	public R selectJobWork(Integer jId) {
		List<JobWork> jobWorkList = jobWorkService.selectJobSumList(jId);
		if (jobWorkList.size()==0){
			return R.fail("请添加工作类型");
		}
		return R.data(jobWorkList);
	}
	/**
	 * 查询
	 */
	@GetMapping("/selectJobWorkName")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "工作量计分值第一次添加查询", notes = "第一次添加会默认 加到关系表里默认值为0")
	public R selectJobWork() {
		List<Work> jobWorkList = workService.query().list();
		if (jobWorkList.size()==0){
			return R.fail("请添加工作类型");
		}
		return R.data(jobWorkList);
	}


	/**
	 * 查询
	 */
	@GetMapping("/selectJobOtherP")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "其他绩效内容查询", notes = "第一次添加会默认 加到关系表里默认值为")
	public R selectJobOtherP(Integer jId) {
		List<JobOtherPVo> jobOtherPList = jobOtherPService.selectJobOtherP(jId);
		return R.data(jobOtherPList);
	}

	/**
	 * 岗位字典表 add
	 * @param param
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加",notes = "")
	@ApiOperationSupport(order = 4)
	public R add(@RequestBody Job param) {
		Job em = jobService.getOne(new QueryWrapper<Job>().eq(Job.COL_JOB_NAME,param.getJobName()).eq(Job.COL_J_ID,param.getJId()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		//工作类型 添加
		jobWorkService.add(param.getJId());
		//其他绩效
		jobOtherPService.add(param.getJId());
		jobService.save(param);
		return R.success("添加成功");
	}

	/**
	 * 学历字典表 update
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 5)
	public R update(@RequestBody UpdateJobDto param) {

		jobService.updateById(param);
		if(param.getJobWorkList().size()==0){
			return R.fail("请到工作类型管理添加数据");
		}
		param.getJobWorkList().forEach(s->{
			if(param.getJId().equals(s.getJId())){
				jobWorkService.updateById(s);
			}
		});
		if (param.getJobOtherPList().size()==0){
			return R.fail("请到其他绩效管理添加数据");
		}
		param.getJobOtherPList().forEach(s->{
			if (param.getJId().equals(s.getJId())){
				jobOtherPService.updateById(s);
			}
		});
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
	@ApiOperationSupport(order = 6)
	public R delete(@RequestBody List<Integer> param) {
		for (Integer ids : param) {
			User serviceOne = userService.getOne(new QueryWrapper<User>().eq(User.COL_JC_ID, ids));
			if (ObjectUtil.isNotNull(serviceOne)) {
				return R.fail("该岗位下存在用户，不可删除");
			}
		}
		return R.data(jobService.removeByIds(param));
	}
}
