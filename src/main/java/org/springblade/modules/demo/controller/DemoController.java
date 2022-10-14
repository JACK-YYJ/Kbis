package org.springblade.modules.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.service.KpiAttendanceService;
import org.springblade.modules.performance.service.KpiFixedService;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;
import org.springblade.modules.performance.service.KpiWorkloadService;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.JobCertificateService;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/10/12 14:01
 */
@RestController
@RequestMapping("api/kpi/test")
@Api(tags = "测试接口（不要操作）")
public class DemoController {
	@Autowired
	public KpiAttendanceService kpiAttendanceService;
	@Autowired
	public UserService userService;
	@Autowired
	public KpiOtherPerformanceService kpiOtherPerformanceService;
	@Autowired
	public KpiFixedMapper kpiFixedMapper;
	@Autowired
	private JobCertificateService jobCertificateService;
	@Autowired
	public KpiFixedService kpiFixedService;
	@Autowired
	public KpiWorkloadService kpiWorkloadService;
	/**
	 *查询
	 */
	@GetMapping("/install")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "测试接口", notes = "传入toMonth")
	public R selectAccountingPage(String toMonth) {
		/*出勤率*/
		List<User> userList = userService.lambdaQuery().orderByAsc(User::getUId).list();
		ArrayList<KpiAttendance> kpiAttendances = new ArrayList<>();
		for (User user : userList) {
			KpiAttendance kpiAttendance = new KpiAttendance();
			kpiAttendance.setUserCode(user.getUserCode());
			kpiAttendance.setUserName(user.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtil.parse(toMonth,"yyyy-MM-dd"));
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(DateUtils.getDayOfMonth());
			kpiAttendance.setMonthDay(DateUtils.getDayOfMonth());
			kpiAttendances.add(kpiAttendance);
		}
		kpiAttendanceService.saveBatch(kpiAttendances);
		/*其他绩效*/

		ArrayList<KpiOtherPerformance> kpiopaddList = new ArrayList<>();
		for (User user : userList) {
			KpiOtherPerformance addKpiOp = new KpiOtherPerformance();
			addKpiOp.setUserCode(user.getUserCode());
			addKpiOp.setUserName(user.getUserName());
			addKpiOp.setAttendanceMonth(DateUtil.parse(toMonth,"yyyy-MM-dd"));
			kpiopaddList.add(addKpiOp);
		}
		kpiOtherPerformanceService.saveBatch(kpiopaddList);
		/*固定绩效*/
		List<FixedToMonth>	 fixedToMonthList = kpiFixedMapper.selectToMonth();
		// 工龄系数 和 上岗证 系数  （没数据 会出现数组下标越界）
		JobCertificate xs = jobCertificateService.query().list().get(0);
		fixedToMonthList.forEach(s->{
			KpiFixed fixed = new KpiFixed();
			fixed.setUserCode(s.getUserCode());
			fixed.setUserName(s.getUserName());
			fixed.setAttendanceMonth(DateUtil.parse(toMonth,"yyyy-MM-dd"));
			fixed.setPositionScore(s.getPositionScore());	//职称分值A
			fixed.setDegreeScore(s.getDegreeScore());		//学历分值B
			fixed.setSeniority(s.getSeniority());			//工龄
			fixed.setSeniorityScore(s.getSeniority().multiply(xs.getAgeFactor()));//工龄分值 C
			fixed.setJcSum(s.getJcSum().multiply(xs.getJobCertificateFactor())); // 上岗证分值
			//合计分值
			fixed.setFixedCountScore(s.getPercentage().multiply(
				(s.getPositionScore().add(
					s.getDegreeScore().add(
						fixed.getSeniorityScore().add(
							fixed.getJcSum()
							//三基考试
						)
					)
				))
			));
			if(s.getJobGs()==0){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore());
			}
			if(s.getJobGs()==1){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore().multiply(s.getJobRatio()));
			}
			if(s.getJobGs()==2){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore().multiply(s.getJobRatio().multiply(s.getPercentage())));
			}
			if(s.getJobGs()==3){
				fixed.setFixedCorrectionScore(BigDecimal.valueOf(0));
			}
			kpiFixedService.save(fixed);
		});
		/*工作量*/
		ArrayList<KpiWorkload> KpiWorkload = new ArrayList<>();
		for (User user : userList) {
			KpiWorkload kpiWorkload = new KpiWorkload();
			kpiWorkload.setUserCode(user.getUserCode());
			kpiWorkload.setUserName(user.getUserName());
			kpiWorkload.setAttendanceMonth(DateUtil.parse(toMonth,"yyyy-MM-dd"));
			KpiWorkload.add(kpiWorkload);
		}
		kpiWorkloadService.saveBatch(KpiWorkload);
		return R.success("wc");
	}
//	public static void main(String[] args) {
//
//		System.out.println(DateUtils.getDayOfMonth());
//	}
}
