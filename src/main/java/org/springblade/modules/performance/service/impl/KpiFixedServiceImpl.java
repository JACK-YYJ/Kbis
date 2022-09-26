package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.PercentageVo;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.JobCertificateService;
import org.springblade.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.service.KpiFixedService;
 /**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Service
public class KpiFixedServiceImpl extends ServiceImpl<KpiFixedMapper, KpiFixed> implements KpiFixedService{
	 @Autowired
	 private UserService userService;
	@Autowired
	private JobCertificateService jobCertificateService;
	 @Override
	 public IPage<KpiFixed> selectfixedPage(IPage<Object> page, String toMonth, String idOrName) {
		IPage<KpiFixed> selectPage =  baseMapper.selectfixedPage(page,toMonth);
		 String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		if (selectPage.getRecords().size()==0&&format.equals(toMonth)){
		 List<FixedToMonth>	 fixedToMonthList = baseMapper.selectToMonth();
		 	// 工龄系数 和 上岗证 系数  （没数据 会出现数组下标越界）
			JobCertificate xs = jobCertificateService.query().list().get(0);
			fixedToMonthList.forEach(s->{
			 KpiFixed fixed = new KpiFixed();
			 fixed.setUserCode(s.getUserCode());
			 fixed.setUserName(s.getUserName());
			 fixed.setAttendanceMonth(s.getAttendanceMonth());
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
			 this.save(fixed);
		 });
		}
		if (idOrName != null) {
			 IPage<KpiFixed> kpiFixedIPage = baseMapper.selectidOrName(page, idOrName, toMonth);
			 return kpiFixedIPage;
		}
	 	return selectPage;
	 }

	 @Override
	 public void updateByOne(KpiFixed param) {
		 PercentageVo p = baseMapper.selectPercentageVo(param.getAttendanceMonth(),param.getUserCode());//出勤率 和 公式
			//合计分值
		 param.setFixedCountScore(p.getPercentage().multiply(
			 (param.getPositionScore().add(
				 param.getDegreeScore().add(
					 param.getSeniorityScore().add(
						 param.getJcSum()
							 //三基考试
							 .add(param.getThreeExam())
					 )
				 )
			 ))
		 ));
		 if(p.getJobGs()==0){// 科室主任 、科室副主任  合计分值
			 param.setFixedCorrectionScore(param.getFixedCountScore());
		 }
		 if(p.getJobGs()==1){//合计分值*岗位系数
			 param.setFixedCorrectionScore(param.getFixedCountScore().multiply(p.getJobRatio()));
		 }
		 if(p.getJobGs()==2){//合计分值*岗位系数*出勤率
			 param.setFixedCorrectionScore(param.getFixedCountScore().multiply(p.getJobRatio().multiply(p.getPercentage())));
		 }
		 if(p.getJobGs()==3){//无
			 param.setFixedCorrectionScore(BigDecimal.valueOf(0));
		 }
		 this.updateById(param);
	 }

	 @Override
	 public R updateByList(List<KpiFixed> kpiFixedList) {
	 	List<R> rList =new ArrayList<>();
		 kpiFixedList.forEach(param->{
			 //校验工号
			 User one = userService.getOne(new QueryWrapper<User>().eq(User.COL_USER_CODE, param.getUserCode()));
			 if(one==null){
				 rList.add(R.data(one));
			 }
			 this.updateByOne(param);
		 });
		 if (ObjectUtil.isAllNotEmpty(rList)){
		 	return R.fail("校验到Excel不存在的工号");
		 }
		 return R.success("编辑成功");
	 }
 }
