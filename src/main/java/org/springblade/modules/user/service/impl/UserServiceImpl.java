package org.springblade.modules.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.mapper.KpiOtherPerformanceMapper;
import org.springblade.modules.performance.service.KpiAttendanceService;
import org.springblade.modules.performance.service.KpiFixedService;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;
import org.springblade.modules.performance.service.KpiWorkloadService;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.mapper.OtherPerformanceMapper;
import org.springblade.modules.user.service.JobCertificateService;
import org.springblade.modules.user.service.OtherPerformanceService;
import org.springblade.modules.user.vo.UserVo;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.mapper.UserMapper;
import org.springblade.modules.user.service.UserService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	public KpiAttendanceService kpiAttendanceService;
	@Autowired
	private KpiFixedService kpiFixedService;
	@Autowired
	private KpiOtherPerformanceService kpiOtherPerformanceService;
	@Autowired
	private KpiFixedMapper kpiFixedMapper;
	@Autowired
	private JobCertificateService jobCertificateService;
	@Autowired
	private KpiWorkloadService kpiWorkloadService;


    @Override
    public IPage<UserVo> selectUserPage(IPage<Object> page, UserDto param) {
        Page<UserVo> userVoPage = baseMapper.selectUserPage(page, param);
        return userVoPage;
    }

	@Override
	public void addIngMonthAttenddance() {
		List<User> userList = this.lambdaQuery().orderByAsc(User::getUId).list();
		ArrayList<KpiAttendance> kpiAttendances = new ArrayList<>();
		for (User user : userList) {
			KpiAttendance kpiAttendance = new KpiAttendance();
			kpiAttendance.setUserCode(user.getUserCode());
			kpiAttendance.setUserName(user.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtils.getNowDate());
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(23);
			kpiAttendance.setMonthDay(23);
			kpiAttendances.add(kpiAttendance);
		}
		kpiAttendanceService.saveBatch(kpiAttendances);
	}

	@Override
	public void addCompute(User param) {
		List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiOtherPerformance> kpiOtherPerformanceList = kpiOtherPerformanceService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiFixed> kpiFixedList 		  = kpiFixedService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiWorkload> kpiWorkloads 		  = kpiWorkloadService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		//出勤率 添加
		if(!( kpiAttendanceList.size()==0)){
			KpiAttendance kpiAttendance = new KpiAttendance();
			kpiAttendance.setUserCode(param.getUserCode());
			kpiAttendance.setUserName(param.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtils.getNowDate());
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(23);
			kpiAttendance.setMonthDay(23);
			kpiAttendanceService.save(kpiAttendance);
		}
		//其他绩效 添加
		if (!(kpiOtherPerformanceList.size()==0)){
			KpiOtherPerformance addKpiOp = new KpiOtherPerformance();
			addKpiOp.setUserCode(param.getUserCode());
			addKpiOp.setUserName(param.getUserName());
			addKpiOp.setAttendanceMonth(DateUtils.getNowDate());
			kpiOtherPerformanceService.save(addKpiOp);
		}
		//固定绩效 用户添加
		FixedToMonth fixedToMonth = kpiFixedMapper.selectToMonthOne(param.getUserCode());
		JobCertificate xs = jobCertificateService.query().list().get(0);
		if (!(kpiFixedList.size()==0)){
			KpiFixed fixed = new KpiFixed();
			fixed.setUserCode(fixedToMonth.getUserCode());
			fixed.setUserName(fixedToMonth.getUserName());
			fixed.setAttendanceMonth(fixedToMonth.getAttendanceMonth());
			fixed.setPositionScore(fixedToMonth.getPositionScore());	//职称分值A
			fixed.setDegreeScore(fixedToMonth.getDegreeScore());		//学历分值B
			fixed.setSeniority(fixedToMonth.getSeniority());			//工龄
			fixed.setSeniorityScore(fixedToMonth.getSeniority().multiply(xs.getAgeFactor()));//工龄分值 C
			fixed.setJcSum(fixedToMonth.getJcSum().multiply(xs.getJobCertificateFactor())); // 上岗证分值
			//合计分值
			fixed.setFixedCountScore(fixedToMonth.getPercentage().multiply(
				(fixedToMonth.getPositionScore().add(
					fixedToMonth.getDegreeScore().add(
						fixed.getSeniorityScore().add(
							fixed.getJcSum()
							//三基考试
						)
					)
				))
			));
			if(fixedToMonth.getJobGs()==0){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore());
			}
			if(fixedToMonth.getJobGs()==1){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore().multiply(fixedToMonth.getJobRatio()));
			}
			if(fixedToMonth.getJobGs()==2){
				fixed.setFixedCorrectionScore(fixed.getFixedCountScore().multiply(fixedToMonth.getJobRatio().multiply(fixedToMonth.getPercentage())));
			}
			if(fixedToMonth.getJobGs()==3){
				fixed.setFixedCorrectionScore(BigDecimal.valueOf(0));
			}
			kpiFixedService.save(fixed);
		}
		//工作量绩效 添加
		if (!(kpiWorkloads.size()==0)){
			KpiWorkload kpiWorkload = new KpiWorkload();
			kpiWorkload.setUserCode(param.getUserCode());
			kpiWorkload.setUserName(param.getUserName());
			kpiWorkload.setAttendanceMonth(DateUtils.getNowDate());
			kpiWorkloadService.save(kpiWorkload);
		}
	}

}





