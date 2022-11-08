package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.AccountingDto;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.mapper.KpiAccountingMapper;
import org.springblade.modules.performance.mapper.KpiFixedMapper;
import org.springblade.modules.performance.service.KpiAccountingService;
import org.springblade.modules.performance.service.KpiPersonalService;
import org.springblade.modules.performance.vo.PercentageVo;
import org.springblade.modules.performance.vo.WorkSumByUserCode;
import org.springblade.modules.performance.vo.kpiWorkloadVo;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.peer.LightweightPeer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.mapper.KpiWorkloadMapper;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.service.KpiWorkloadService;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Service
public class KpiWorkloadServiceImpl extends ServiceImpl<KpiWorkloadMapper, KpiWorkload> implements KpiWorkloadService {
	@Autowired
	public UserService userService;
	@Autowired
	public KpiFixedMapper kpiFixedMapper;
	@Autowired
	public KpiAccountingMapper KpiAccountingMapper;
	@Autowired
	public KpiAccountingService kpiAccountingService;
	@Autowired
	public KpiPersonalService kpiPersonalService;

	@Override
	public IPage<KpiWorkload> selectWorkloadPage(IPage<Object> page, String toMonth, String idOrName) {
		Page<KpiWorkload> kpiWorkloadPage = baseMapper.kpiWorkloadPage(page, toMonth);
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		if (kpiWorkloadPage.getRecords().size() == 0 && format.equals(toMonth)) {
			List<User> userList = userService.lambdaQuery().orderByAsc(User::getUId).list();
			ArrayList<KpiWorkload> kpiAttendances = new ArrayList<>();
			for (User user : userList) {
				KpiWorkload kpiWorkload = new KpiWorkload();
				kpiWorkload.setUId(user.getUId());
				kpiWorkload.setUserCode(user.getUserCode());
				kpiWorkload.setUserName(user.getUserName());
				kpiWorkload.setAttendanceMonth(DateUtils.getNowDate());

				kpiAttendances.add(kpiWorkload);
			}
			this.saveBatch(kpiAttendances);
		}
		if (idOrName != null) {
			IPage<KpiWorkload> kpiWorkload = baseMapper.selectidOrName(page, idOrName, toMonth);
			return kpiWorkload;
		}
		return kpiWorkloadPage;

	}

	@Override
	public R updateByWorkSum(KpiWorkload param) {
		WorkSumByUserCode PlainFilmSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 1);
		WorkSumByUserCode BowelSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 2);
		WorkSumByUserCode BreastSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 3);
		WorkSumByUserCode ctFlatSweepSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 4);
		WorkSumByUserCode ctIntensifierSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 5);
		WorkSumByUserCode ctPositioningSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 6);
		WorkSumByUserCode ctHemalSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 7);
		WorkSumByUserCode mrFlatSweepSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 8);
		WorkSumByUserCode mrIntensifierSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 9);
		WorkSumByUserCode mrImageSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 10);
		WorkSumByUserCode mrBreastSum = baseMapper.setectWorkSumByUserCode(param.getUserCode(), 11);
		if (ObjectUtil.isEmpty(PlainFilmSum)&&
			ObjectUtil.isEmpty(BowelSum)&&
			ObjectUtil.isEmpty(BreastSum)&&
			ObjectUtil.isEmpty(ctFlatSweepSum)&&
			ObjectUtil.isEmpty(ctIntensifierSum)&&
			ObjectUtil.isEmpty(ctPositioningSum)&&
			ObjectUtil.isEmpty(ctHemalSum)&&
			ObjectUtil.isEmpty(mrFlatSweepSum)&&
			ObjectUtil.isEmpty(mrIntensifierSum)&&
			ObjectUtil.isEmpty(mrImageSum)&&
			ObjectUtil.isEmpty(mrBreastSum)) {
			return R.fail("工作量分值数据缺失");
		}

		//计算 总和
		param.setWorkSum(
			param.getPlainFilmSum().multiply(PlainFilmSum.getWSum())
				.add(param.getBowelSum().multiply(BowelSum.getWSum())
					.add(param.getBreastSum().multiply(BreastSum.getWSum())
						.add(param.getCtFlatSweepSum().multiply(ctFlatSweepSum.getWSum())
							.add(param.getCtIntensifierSum().multiply(ctIntensifierSum.getWSum())
								.add(param.getCtPositioningSum().multiply(ctPositioningSum.getWSum())
									.add(param.getCtHemalSum().multiply(ctHemalSum.getWSum())
										.add(param.getMrFlatSweepSum().multiply(mrFlatSweepSum.getWSum())
											.add(param.getMrIntensifierSum().multiply(mrIntensifierSum.getWSum())
												.add(param.getMrImageSum().multiply(mrImageSum.getWSum())
													.add(param.getMrBreastSum().multiply(mrBreastSum.getWSum()))
												)
											)
										)
									)
								)
							)
						)
					)
				)
		);
		baseMapper.updateById(param);
//		List<kpiWorkloadVo> kWVoList = baseMapper.selectWorkLoadVo(param.getAttendanceMonth());
//		List<kpiWorkloadVo> kpiWorkloadVoStream = kWVoList.stream()
//			.filter(s ->
//				(s.getWorkSum()
//					.compareTo(  (BigDecimal.valueOf(0))  ) !=0))
//			.collect(Collectors.toList());
//		//A医师
//		List<kpiWorkloadVo> phykWVoList = kpiWorkloadVoStream.stream()
//			.filter(s -> s.getJobType().equals(0))
//			.collect(Collectors.toList());
//		//B医技
//		List<kpiWorkloadVo> medkWVoList = kpiWorkloadVoStream.stream()
//			.filter(s -> s.getJobType().equals(1) )
//			.collect(Collectors.toList());
//		if (phykWVoList.size()!=0&&medkWVoList.size()!=0){
//			this.updateVerify(param);
//		}
		return R.success("编辑成功");
	}

	/**
	 * 矫正分值
	 * @param param
	 * @return
	 */
	private R updateVerify(KpiWorkload param) {
		//根据 公式生成 矫正工作量绩效分值

		List<kpiWorkloadVo> kWVoList = baseMapper.selectWorkLoadVo(param.getAttendanceMonth());
		List<kpiWorkloadVo> kpiWorkloadVoStream = kWVoList.stream()
			.filter(s ->
				(s.getWorkSum()
					.compareTo(  (BigDecimal.valueOf(0))  ) !=0))
			.collect(Collectors.toList());
		//A医师
		List<kpiWorkloadVo> phykWVoList = kpiWorkloadVoStream.stream()
			.filter(s -> s.getJobType()==0)
			.collect(Collectors.toList());
		//B医技
		List<kpiWorkloadVo> medkWVoList = kpiWorkloadVoStream.stream()
			.filter(s -> s.getJobType()==1 )
			.collect(Collectors.toList());

		if(ObjectUtil.isAllEmpty(phykWVoList)&&ObjectUtil.isAllEmpty(medkWVoList)){//if 医师、医技avg==0
			BigDecimal phyAverage = BigDecimal.valueOf(0);
			BigDecimal medAverage = BigDecimal.valueOf(0);
			this.computeAvg(phyAverage,medAverage,param);

		}else {
			if (ObjectUtil.isAllEmpty(phykWVoList)){//if 医师avg==0
				BigDecimal phyAverage = BigDecimal.valueOf(0);
				BigDecimal medAverage = medkWVoList.stream().map(kpiWorkloadVo::getWorkSum)    //求平均值
					.reduce(BigDecimal.ZERO, BigDecimal::add)
					.divide(BigDecimal.valueOf(medkWVoList.size()), 4, BigDecimal.ROUND_HALF_UP);
				this.computeAvg(phyAverage,medAverage,param);
			}else {//if 医技avg==0
				if(ObjectUtil.isAllEmpty(medkWVoList)){
					BigDecimal medAverage = BigDecimal.valueOf(0);
					BigDecimal phyAverage = phykWVoList.stream().map(kpiWorkloadVo::getWorkSum)    //求平均值
						.reduce(BigDecimal.ZERO, BigDecimal::add)
						.divide(BigDecimal.valueOf(phykWVoList.size()), 4, BigDecimal.ROUND_HALF_UP);
					this.computeAvg(phyAverage,medAverage,param);
				}else {
					BigDecimal medAverage = medkWVoList.stream().map(kpiWorkloadVo::getWorkSum)    //求平均值
						.reduce(BigDecimal.ZERO, BigDecimal::add)
						.divide(BigDecimal.valueOf(medkWVoList.size()), 4, BigDecimal.ROUND_HALF_UP);
					BigDecimal phyAverage = phykWVoList.stream().map(kpiWorkloadVo::getWorkSum)    //求平均值
						.reduce(BigDecimal.ZERO, BigDecimal::add)
						.divide(BigDecimal.valueOf(phykWVoList.size()), 4, BigDecimal.ROUND_HALF_UP);
					this.computeAvg(phyAverage,medAverage,param);
				}
			}
		}
		return R.success("ok");
	}
	public void computeAvg (BigDecimal phyAverage,BigDecimal medAverage,KpiWorkload param){
		PercentageVo p = kpiFixedMapper.selectPercentageVo(param.getAttendanceMonth(), param.getUserCode());
		if (p.getWorkGs() == 0) {
			//医师 矫正分值
			if (p.getJobType() == 0) {
				// a>b  平均值 大于 总
				if (phyAverage.compareTo(param.getWorkSum()) > -1) {
					param.setWorkCorrect(
						phyAverage
							.multiply(p.getJobRatio()
								.multiply(p.getPercentage())
							)
					);
					// a<b
				} else {
					param.setWorkCorrect(param.getWorkSum());
				}
			}
			//医技 矫正分值
			if (p.getJobType() == 1) {
				if (medAverage.compareTo(param.getWorkSum()) > -1) {
					param.setWorkCorrect(
						medAverage
							.multiply(p.getJobRatio()
								.multiply(p.getPercentage())
							)
					);
				} else {
					param.setWorkCorrect(param.getWorkSum());
				}
			}
		}
		if (p.getWorkGs() == 1) {//总工作量
			param.setWorkCorrect(param.getWorkSum());
		}
		if (p.getWorkGs() == 2) {
			//医师 矫正分值
			if (p.getJobType() == 0) {
				param.setWorkCorrect(
					phyAverage
						.multiply(p.getJobRatio()
							.multiply(p.getPercentage())
						)
				);
				//医技 矫正分值
			}
			if (p.getJobType() == 1) {
				param.setWorkCorrect(
					medAverage
						.multiply(p.getJobRatio()
							.multiply(p.getPercentage())
						)
				);
			}
		}
		baseMapper.updateById(param);
	}

	@Override
	public R updateByList(List<KpiWorkload> kpiFixedList) {
		List<KpiWorkload> data = new ArrayList<>();
		List<R> R2 = new ArrayList<>();
		List<R> rList = new ArrayList<>();
		kpiFixedList.forEach(param -> {
			//校验工号
			User one = userService.getOne(new QueryWrapper<User>().eq(User.COL_USER_CODE, param.getUserCode()));
			if (one == null) {
				rList.add(R.data(one));
			}
			//循环校验
			R r = this.updateByWorkSum(param);

			if (!r.isSuccess()) {
				data.add(param);
			}
		});

//		kpiFixedList.forEach(s->{
//			KpiWorkload param = this.getById(s);
//			R r2 = this.updateVerify(param);
//			R2.add(r2);
//		});
		if (ObjectUtil.isAllNotEmpty(data)) {
			return R.fail("工作量分值数据缺失");
		}
		if (ObjectUtil.isAllNotEmpty(rList)) {
			return R.fail("校验到Excel不存在的工号");
		}
		if (ObjectUtil.isAllNotEmpty(R2)) {
			return R.fail("暂无工作量，计算失效");
		}
		return R.success("保存成功");
	}

	@Override
	public List<KpiWorkload> selectToMonth(String format) {
		List<KpiWorkload> kpiWorkloadList =  baseMapper.selectToMonth(format);
		return kpiWorkloadList;
	}

	@Override
	public R computeByList(List<KpiWorkload> kpiFixedList,String toMonth) {
		List<R> R1 = new ArrayList<>();
		List<R> R2 = new ArrayList<>();
		List<R> Rysyj=new ArrayList<>();
		List<R> Rp = new ArrayList<>();
		kpiFixedList.forEach(s->{
			s.setComputeStatus(1);//计算 固定绩效
			R r =this.updateByWorkSum(s);//计算合计
			R r2 = this.updateVerify(s);

			if (("nop")== r2.getMsg()){
				Rp.add(r2);
			}
			if(("ysyj/ by zero")==r2.getMsg()){
				Rysyj.add(r2);
			}
		});
		if (ObjectUtil.isAllNotEmpty(Rp)) {
			return R.fail("当前数据考勤有误，请联系管理员");
		}
//		KpiAccounting kpiAccounting = KpiAccountingMapper.selectByToMonth(toMonth);
//		if(ObjectUtil.isNotEmpty(kpiAccounting)){
//			AccountingDto accountingDto = new AccountingDto();
//
//			accountingDto.setToMonth(toMonth);
//			accountingDto.setMedFixedCoefficient(kpiAccounting.getMedFixedCoefficient());
//			accountingDto.setPerformanceSum(kpiAccounting.getPerformanceSum());
//			accountingDto.setPhyFixedCoefficient(kpiAccounting.getPhyFixedCoefficient());
//			accountingDto.setPhyCentum(kpiAccounting.getPhyCentum());
//
//			kpiAccountingService.saves(accountingDto);
//			kpiPersonalService.updateByPersonal(toMonth);
//		}
		return R.success("计算成功");
	}
}

