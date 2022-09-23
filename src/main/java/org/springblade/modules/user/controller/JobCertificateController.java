package org.springblade.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.user.entity.Degree;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.JobCertificateService;
import org.springblade.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/26 12:53
 */
@RestController
@RequestMapping("api/set/JobCertificate")
@Api(tags = "上岗证管理（字典表）")
@CrossOrigin
public class JobCertificateController {
	@Autowired
	private JobCertificateService jobCertificateService;
	@Autowired
	public UserService userService;
	/**
	 * 上岗证管理
	 */
	@GetMapping("/selectJobCertificatePage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "idOrNaem")
	public R<IPage<JobCertificate>> selectJobCertificatePage(Query page, String idOrName) {
		IPage<JobCertificate> pages = jobCertificateService.selectJobCertificatePage(Condition.getPage(page),idOrName);
		return R.data(pages);
	}
	/**
	 * 上岗证管理 add
	 * @param param
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiOperationSupport(order = 2)
	public R add(@RequestBody JobCertificate param) {
		JobCertificate em = jobCertificateService.getOne(new QueryWrapper<JobCertificate>().eq(JobCertificate.COL_JOB_CERTIFICATE_NAME,param.getJobCertificateName()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		jobCertificateService.save(param);
		return R.success("添加成功");
	}

	/**
	 * 上岗证管理 update
	 * @param param
	 * @return
	 */

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody JobCertificate param) {
		jobCertificateService.updateById(param);
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
		int count = jobCertificateService.count();
		if(count==0){
			jobCertificateService.removeByIds(param);
		}else {
			User user = userService.lambdaQuery()
				.orderByDesc(User::getJcId).list().get(0);

			if(user.getJcId()>=count){
				return R.fail("该上岗证已有用户");
			}
			jobCertificateService.removeByIds(param);
		}
		return R.success("删除成功");
	}

}
