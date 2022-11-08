package org.springblade.modules.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.*;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.mapper.KpiPersonalMapper;
import org.springblade.modules.performance.service.*;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.performance.vo.KpiPersonalVo;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.service.JobCertificateService;
import org.springblade.modules.user.vo.UserVo;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	@Autowired
	private KpiPersonalMapper kpiPersonalMapper;


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
		List<KpiAttendance> kpiAttendanceList = kpiAttendanceService.selectToMonth(DateUtils.getTodayDate());
		List<KpiOtherPerformance> kpiOtherPerformanceList = kpiOtherPerformanceService.selectToMonth(DateUtils.getTodayDate());
		List<KpiFixed> kpiFixedList 		  = kpiFixedService.selectToMonth(DateUtils.getTodayDate());
		List<KpiWorkload> kpiWorkloads 		  = kpiWorkloadService.selectToMonth(DateUtils.getTodayDate());
		List<KpiPersonalVo> kpiPersonalList     = kpiPersonalMapper.selectByMonth(DateUtils.getTodayDate());
		int sum = kpiAttendanceService.count() /(this.count()-1);   //含有月份数据的份数
		for (int i = 0; i < sum; i++) {
			//出勤率 添加
			if(!( kpiAttendanceList.size()==0)){
				KpiAttendance kpiAttendance = new KpiAttendance();
				kpiAttendance.setUId(one.getUId());
				kpiAttendance.setUserCode(one.getUserCode());
				kpiAttendance.setUserName(one.getUserName());
				kpiAttendance.setAttendanceMonth(DateUtils.getLastByi_yyyyMM(i));//自己封装的工具类
				kpiAttendance.setAttendanceState(1);
				kpiAttendance.setAttendanceDay(DateUtils.getDayOfToMonth(i));//自己封装的工具类-指定月份的时间天数 当前时间：i=0
				kpiAttendance.setMonthDay(DateUtils.getDayOfToMonth(i));//自己封装的工具类-指定月份的时间天数 当前时间：i=0
				kpiAttendanceService.save(kpiAttendance);
			}
			//其他绩效 添加
			if (!(kpiOtherPerformanceList.size()==0)){
				KpiOtherPerformance addKpiOp = new KpiOtherPerformance();
				addKpiOp.setUId(one.getUId());
				addKpiOp.setUserCode(one.getUserCode());
				addKpiOp.setUserName(one.getUserName());
				addKpiOp.setAttendanceMonth(DateUtils.getLastByi_yyyyMM(i));
				kpiOtherPerformanceService.save(addKpiOp);
			}
			//固定绩效 用户添加
			FixedToMonth fixedToMonth = kpiFixedMapper.selectToMonthOneByUser(one.getUserCode(),DateUtils.getLastByi_yyyyMM(i));
			JobCertificate xs = jobCertificateService.query().list().get(0);
			if (!(kpiFixedList.size()==0)){
				KpiFixed fixed = new KpiFixed();
				fixed.setUId(fixedToMonth.getUId());
				fixed.setUserCode(fixedToMonth.getUserCode());
				fixed.setUserName(fixedToMonth.getUserName());
				fixed.setAttendanceMonth(DateUtils.getLastByi_yyyyMM(i));
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
				kpiWorkload.setUId(one.getUId());
				kpiWorkload.setUserCode(one.getUserCode());
				kpiWorkload.setUserName(one.getUserName());
				kpiWorkload.setAttendanceMonth(DateUtils.getLastByi_yyyyMM(i));
				kpiWorkloadService.save(kpiWorkload);
			}
			if(!(kpiPersonalList.size()==0)){
				KpiPersonal kpiPersonal = new KpiPersonal();
				kpiPersonal.setUId(one.getUId());
				kpiPersonal.setUserCode(one.getUserCode());
				kpiPersonal.setUserName(one.getUserName());
				kpiPersonal.setAttendanceMonth(DateUtils.getLastByi_yyyyMM(i));
				kpiPersonalService.save(kpiPersonal);
			}
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

//				long result = kpiAttendance.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//				if(result>=0){  //两个月之前的数据
					kpiAttendanceService.updateById(kpiAttendance);
//				}
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiOtherPerformanceList)){
			for (KpiOtherPerformance kpiOtherPerformance : kpiOtherPerformanceList) {
				kpiOtherPerformance.setUserCode(param.getUserCode());
				kpiOtherPerformance.setUserName(param.getUserName());

//				long result = kpiOtherPerformance.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//				if(result>=0){
					kpiOtherPerformanceService.updateById(kpiOtherPerformance);
//				}
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiFixedList)){
			for (KpiFixed kpiFixed : kpiFixedList) {
				JobCertificate xs = jobCertificateService.query().list().get(0);
				FixedToMonth fixedToMonth = kpiFixedMapper.selectToMonthOneByUser(param.getUserCode(),kpiFixed.getAttendanceMonth());
				kpiFixed.setUserCode(param.getUserCode());
				kpiFixed.setUserName(param.getUserName());

//				long result = kpiFixed.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//				if(result>=0){
				kpiFixed.setPositionScore(fixedToMonth.getPositionScore());	//职称分值A
				kpiFixed.setDegreeScore(fixedToMonth.getDegreeScore());		//学历分值B
				kpiFixed.setSeniority(fixedToMonth.getSeniority());			//工龄
				kpiFixed.setSeniorityScore(fixedToMonth.getSeniority().multiply(xs.getAgeFactor()));//工龄分值 C
				kpiFixed.setJcSum(fixedToMonth.getJcSum().multiply(xs.getJobCertificateFactor())); // 上岗证分值
				//合计分值
				kpiFixed.setFixedCountScore(fixedToMonth.getPercentage().multiply(
					(fixedToMonth.getPositionScore().add(
						fixedToMonth.getDegreeScore().add(
							kpiFixed.getSeniorityScore().add(
								kpiFixed.getJcSum()
								//三基考试
							)
						)
					))
				));
				if(fixedToMonth.getJobGs()==0){
					kpiFixed.setFixedCorrectionScore(kpiFixed.getFixedCountScore());
				}
				if(fixedToMonth.getJobGs()==1){
					kpiFixed.setFixedCorrectionScore(kpiFixed.getFixedCountScore().multiply(fixedToMonth.getJobRatio()));
				}
				if(fixedToMonth.getJobGs()==2){
					kpiFixed.setFixedCorrectionScore(kpiFixed.getFixedCountScore().multiply(fixedToMonth.getJobRatio().multiply(fixedToMonth.getPercentage())));
				}
				if(fixedToMonth.getJobGs()==3){
					kpiFixed.setFixedCorrectionScore(BigDecimal.valueOf(0));
				}
				kpiFixedService.updateById(kpiFixed);
//				}

			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiWorkloadList)){
			for (KpiWorkload kpiWorkload : kpiWorkloadList) {
				kpiWorkload.setUserCode(param.getUserCode());
				kpiWorkload.setUserName(param.getUserName());
//				long result = kpiWorkload.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//				if(result>0){
					kpiWorkloadService.updateById(kpiWorkload);
//				}
			}
		}
		if (ObjectUtil.isAllNotEmpty(kpiPersonalList)){
			for (KpiPersonal kpiPersonal : kpiPersonalList) {
				kpiPersonal.setUserCode(param.getUserCode());
				kpiPersonal.setUserName(param.getUserName());
				kpiPersonal.setJobName(param.getJobName());
//				long result = kpiPersonal.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//				if(result>0){
					kpiPersonalService.updateById(kpiPersonal);
//				}

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
//					long result = kpiAttendance.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//					if(result>=0){  //两个月之前的数据
						kpiAttendanceService.removeById(kpiAttendance);
//					}
				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiOtherPerformanceList)){
				for (KpiOtherPerformance kpiOtherPerformance : kpiOtherPerformanceList) {
//					long result = kpiOtherPerformance.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//					if(result>=0){
						kpiOtherPerformanceService.removeById(kpiOtherPerformance);
//					}

				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiFixedList)){
				for (KpiFixed kpiFixed : kpiFixedList) {
//					long result = kpiFixed.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//					if(result>=0){
						kpiFixedService.removeById(kpiFixed);
//					}

				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiWorkloadList)){
				for (KpiWorkload kpiWorkload : kpiWorkloadList) {
//					long result = kpiWorkload.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//					if(result>0){
						kpiWorkloadService.removeById(kpiWorkload);
//					}

				}
			}
			if (ObjectUtil.isAllNotEmpty(kpiPersonalList)){
				for (KpiPersonal kpiPersonal : kpiPersonalList) {
//					long result = kpiPersonal.getAttendanceMonth().getTime()-DateUtils.getLast_yyyyMM().getTime();
//					if(result>0){
						kpiPersonalService.removeById(kpiPersonal);
//					}
				}
			}
		}
	}





}





