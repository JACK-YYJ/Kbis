package org.springblade.modules.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.*;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.service.*;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.service.JobCertificateService;
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
	@Autowired
	private KpiPersonalService kpiPersonalService;


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
			kpiAttendance.setUId(user.getUId());
			kpiAttendance.setUserCode(user.getUserCode());
			kpiAttendance.setUserName(user.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtils.getNowDate());
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(DateUtils.getDayOfMonth());
			kpiAttendance.setMonthDay(DateUtils.getDayOfMonth());
			kpiAttendances.add(kpiAttendance);
		}
		kpiAttendanceService.saveBatch(kpiAttendances);
	}

	@Override
	public void addCompute(User param) {
		//差出 Uid
		User one = this.getOne(new QueryWrapper<User>().eq(User.COL_USER_CODE, param.getUserCode()));
		List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiOtherPerformance> kpiOtherPerformanceList = kpiOtherPerformanceService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiFixed> kpiFixedList 		  = kpiFixedService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		List<KpiWorkload> kpiWorkloads 		  = kpiWorkloadService.selectToMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
		//出勤率 添加
		if(!( kpiAttendanceList.size()==0)){
			KpiAttendance kpiAttendance = new KpiAttendance();
			KpiAttendance kpiAttendance1 = new KpiAttendance();
			kpiAttendance.setUId(one.getUId());
			kpiAttendance.setUserCode(one.getUserCode());
			kpiAttendance.setUserName(one.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtils.getNow_yyyyMM());
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(DateUtils.getDayOfMonth());
			kpiAttendance.setMonthDay(DateUtils.getDayOfMonth());
			kpiAttendanceService.save(kpiAttendance);

//			kpiAttendance1.setUId(one.getUId());
//			kpiAttendance1.setUserCode(one.getUserCode());
//			kpiAttendance1.setUserName(one.getUserName());
//			kpiAttendance1.setAttendanceMonth(DateUtils.getLast_yyyyMM());
//			kpiAttendance1.setAttendanceState(1);
//			kpiAttendance1.setAttendanceDay(DateUtils.getDayOfMonth());
//			kpiAttendance1.setMonthDay(DateUtils.getDayOfMonth());
//			kpiAttendanceService.save(kpiAttendance1);
		}
		//其他绩效 添加
		if (!(kpiOtherPerformanceList.size()==0)){
			KpiOtherPerformance addKpiOp = new KpiOtherPerformance();
			addKpiOp.setUId(one.getUId());
			addKpiOp.setUserCode(one.getUserCode());
			addKpiOp.setUserName(one.getUserName());
			addKpiOp.setAttendanceMonth(DateUtils.getNow_yyyyMM());
			kpiOtherPerformanceService.save(addKpiOp);


//			KpiOtherPerformance addKpiOp1 = new KpiOtherPerformance();
//			addKpiOp1.setUId(one.getUId());
//			addKpiOp1.setUserCode(one.getUserCode());
//			addKpiOp1.setUserName(one.getUserName());
//			addKpiOp1.setAttendanceMonth(DateUtils.getLast_yyyyMM());
//			kpiOtherPerformanceService.save(addKpiOp1);
		}
		//固定绩效 用户添加
		FixedToMonth fixedToMonth = kpiFixedMapper.selectToMonthOne(one.getUserCode());
		JobCertificate xs = jobCertificateService.query().list().get(0);
		if (!(kpiFixedList.size()==0)){
			KpiFixed fixed = new KpiFixed();
			fixed.setUId(fixedToMonth.getUId());
			fixed.setUserCode(fixedToMonth.getUserCode());
			fixed.setUserName(fixedToMonth.getUserName());
			fixed.setAttendanceMonth(DateUtils.getNow_yyyyMM());
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


//			KpiFixed fixed1 = new KpiFixed();
//			fixed1.setUId(fixedToMonth.getUId());
//			fixed1.setUserCode(fixedToMonth.getUserCode());
//			fixed1.setUserName(fixedToMonth.getUserName());
//			fixed1.setAttendanceMonth(DateUtils.getLast_yyyyMM());
//			fixed1.setPositionScore(fixedToMonth.getPositionScore());	//职称分值A
//			fixed1.setDegreeScore(fixedToMonth.getDegreeScore());		//学历分值B
//			fixed1.setSeniority(fixedToMonth.getSeniority());			//工龄
//			fixed1.setSeniorityScore(fixedToMonth.getSeniority().multiply(xs.getAgeFactor()));//工龄分值 C
//			fixed1.setJcSum(fixedToMonth.getJcSum().multiply(xs.getJobCertificateFactor())); // 上岗证分值
//			//合计分值
//			fixed1.setFixedCountScore(fixedToMonth.getPercentage().multiply(
//				(fixedToMonth.getPositionScore().add(
//					fixedToMonth.getDegreeScore().add(
//						fixed1.getSeniorityScore().add(
//							fixed1.getJcSum()
//							//三基考试
//						)
//					)
//				))
//			));
//			if(fixedToMonth.getJobGs()==0){
//				fixed1.setFixedCorrectionScore(fixed1.getFixedCountScore());
//			}
//			if(fixedToMonth.getJobGs()==1){
//				fixed1.setFixedCorrectionScore(fixed1.getFixedCountScore().multiply(fixedToMonth.getJobRatio()));
//			}
//			if(fixedToMonth.getJobGs()==2){
//				fixed1.setFixedCorrectionScore(fixed1.getFixedCountScore().multiply(fixedToMonth.getJobRatio().multiply(fixedToMonth.getPercentage())));
//			}
//			if(fixedToMonth.getJobGs()==3){
//				fixed1.setFixedCorrectionScore(BigDecimal.valueOf(0));
//			}
//			kpiFixedService.save(fixed1);
		}
		//工作量绩效 添加
		if (!(kpiWorkloads.size()==0)){
			KpiWorkload kpiWorkload = new KpiWorkload();
			kpiWorkload.setUId(one.getUId());
			kpiWorkload.setUserCode(one.getUserCode());
			kpiWorkload.setUserName(one.getUserName());
			kpiWorkload.setAttendanceMonth(DateUtils.getNow_yyyyMM());
			kpiWorkloadService.save(kpiWorkload);

//			KpiWorkload kpiWorkload1= new KpiWorkload();
//			kpiWorkload1.setUId(one.getUId());
//			kpiWorkload1.setUserCode(one.getUserCode());
//			kpiWorkload1.setUserName(one.getUserName());
//			kpiWorkload1.setAttendanceMonth(DateUtils.getLast_yyyyMM());
//			kpiWorkloadService.save(kpiWorkload1);
		}
	}

	@Override
	public void checkUser(User param) {
		List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.lambdaQuery().eq(KpiAttendance::getUId, param.getUId()).list();
		List<KpiOtherPerformance> kpiOtherPerformanceList = kpiOtherPerformanceService.lambdaQuery().eq(KpiOtherPerformance::getUId, param.getUId()).list();
		List<KpiFixed> kpiFixedList = kpiFixedService.lambdaQuery().eq(KpiFixed::getUId, param.getUId()).list();
		List<KpiWorkload> kpiWorkloadList = kpiWorkloadService.lambdaQuery().eq(KpiWorkload::getUId, param.getUId()).list();
		List<KpiPersonal> kpiPersonalList = kpiPersonalService.lambdaQuery().eq(KpiPersonal::getUId, param.getUId()).list();
		if (ObjectUtil.isAllNotEmpty(kpiAttendanceList)){
			for (KpiAttendance kpiAttendance : kpiAttendanceList) {
				kpiAttendance.setUserCode(param.getUserCode());
				kpiAttendance.setUserName(param.getUserName());
				kpiAttendanceService.updateById(kpiAttendance);
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiOtherPerformanceList)){
			for (KpiOtherPerformance kpiOtherPerformance : kpiOtherPerformanceList) {
				kpiOtherPerformance.setUserCode(param.getUserCode());
				kpiOtherPerformance.setUserName(param.getUserName());
				kpiOtherPerformanceService.updateById(kpiOtherPerformance);
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiFixedList)){
			for (KpiFixed kpiFixed : kpiFixedList) {
				kpiFixed.setUserCode(param.getUserCode());
				kpiFixed.setUserName(param.getUserName());
				kpiFixedService.updateById(kpiFixed);
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiWorkloadList)){
			for (KpiWorkload kpiWorkload : kpiWorkloadList) {
				kpiWorkload.setUserCode(param.getUserCode());
				kpiWorkload.setUserName(param.getUserName());
				kpiWorkloadService.updateById(kpiWorkload);
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiPersonalList)){
			for (KpiPersonal kpiPersonal : kpiPersonalList) {
				kpiPersonal.setUserCode(param.getUserCode());
				kpiPersonal.setUserName(param.getUserName());
				kpiPersonalService.updateById(kpiPersonal);
			}
		}
	}

	@Override
	public void checkDeleteUser(List<Integer> param) {
		for (Integer uId : param) {
			List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.lambdaQuery().eq(KpiAttendance::getUId,uId ).list();
			List<KpiOtherPerformance> kpiOtherPerformanceList = kpiOtherPerformanceService.lambdaQuery().eq(KpiOtherPerformance::getUId, uId).list();
			List<KpiFixed> kpiFixedList = kpiFixedService.lambdaQuery().eq(KpiFixed::getUId,uId).list();
			List<KpiWorkload> kpiWorkloadList = kpiWorkloadService.lambdaQuery().eq(KpiWorkload::getUId, uId).list();
			List<KpiPersonal> kpiPersonalList = kpiPersonalService.lambdaQuery().eq(KpiPersonal::getUId, uId).list();

			if (ObjectUtil.isAllNotEmpty(kpiAttendanceList)){
				for (KpiAttendance kpiAttendance : kpiAttendanceList) {
					kpiAttendanceService.removeById(kpiAttendance);
				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiOtherPerformanceList)){
				for (KpiOtherPerformance kpiOtherPerformance : kpiOtherPerformanceList) {
					kpiOtherPerformanceService.removeById(kpiOtherPerformance);
				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiFixedList)){
				for (KpiFixed kpiFixed : kpiFixedList) {
					kpiFixedService.removeById(kpiFixed);
				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiWorkloadList)){
				for (KpiWorkload kpiWorkload : kpiWorkloadList) {
					kpiWorkloadService.removeById(kpiWorkload);
				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiPersonalList)){
				for (KpiPersonal kpiPersonal : kpiPersonalList) {
					kpiPersonalService.removeById(kpiPersonal);
				}
			}
		}
	}

}





