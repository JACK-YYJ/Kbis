package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.AccountingDto;
import org.springblade.modules.performance.vo.SumVo;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.mapper.KpiAccountingMapper;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.service.KpiAccountingService;

/**
 * @Author 元杰
 * @Date 2022/9/14 14:36
 */

@Service
public class KpiAccountingServiceImpl extends ServiceImpl<KpiAccountingMapper, KpiAccounting> implements KpiAccountingService {

	@Autowired
	public UserService userService;

	@Override
	public KpiAccounting selectAccountingPage(String toMonth) {
		KpiAccounting kpiAccounting = baseMapper.selectByToMonth(toMonth);//根据月份查询

		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		if (ObjectUtil.isEmpty(kpiAccounting) && format.equals(toMonth)) {
			KpiAccounting accounting = new KpiAccounting();
			accounting.setAttendanceMonth(DateUtils.getNowDate());
			this.save(accounting);
			KpiAccounting byId = baseMapper.selectByToMonth(toMonth);
			this.install(byId);
		}
		return kpiAccounting;
	}

	@Override
	public R saves(AccountingDto param) {
		if (param.getToMonth() == null) {
			return R.fail("请输入月份");
		}
		KpiAccounting byId = baseMapper.selectByToMonth(param.getToMonth());

		byId.setPerformanceSum(param.getPerformanceSum());//科室分配绩效总额

		String format = DateUtil.format(byId.getAttendanceMonth(), "yyyy-MM");
		List<SumVo> kpiFixeds = baseMapper.selectSumPhyMed(format);
		BigDecimal num1 = new BigDecimal(1.00);
		BigDecimal opsum = kpiFixeds.stream().map(SumVo::getKpiOpAllPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		byId.setOntherPerformanceSum(opsum);//其他绩效总额

		BigDecimal PerformanceSum = param.getPerformanceSum().subtract(opsum);
		byId.setAllotSum(PerformanceSum);//可分配绩效总额

		byId.setPhyCentum(param.getPhyCentum());//医师分配占比
		BigDecimal MedCentum = num1.subtract(param.getPhyCentum());
		byId.setMedCentum(MedCentum);//医技分配占比

		byId.setPhyFixedCoefficient(param.getPhyFixedCoefficient());//医师固定绩效分配系数
		BigDecimal PhyWorkCoefficient = num1.subtract(param.getPhyFixedCoefficient());
		byId.setPhyWorkCoefficient(PhyWorkCoefficient);//医师工作量绩效分配系数

		byId.setMedFixedCoefficient(param.getMedFixedCoefficient());//医技固定绩效分配系数
		BigDecimal WorkCoefficient = num1.subtract(param.getMedFixedCoefficient());
		byId.setMedWorkCoefficient(WorkCoefficient);//医技工作量绩效分配系数

		byId.setPhyFixedSum(PerformanceSum.multiply(param.getPhyCentum().multiply(param.getPhyFixedCoefficient())));//医师固定绩效总额
		byId.setPhyWorkSum(PerformanceSum.multiply(param.getPhyCentum().multiply(PhyWorkCoefficient)));//医师工作量绩效总额

		byId.setMedFixedSum(PerformanceSum.multiply(MedCentum.multiply(param.getMedFixedCoefficient())));//医技固定绩效总额
		byId.setMedWorkSum(PerformanceSum.multiply(MedCentum.multiply(WorkCoefficient)));//医技工作量绩效总额

//		BigDecimal PhyFixedCorrectionScore = new BigDecimal(0.00000);
//		BigDecimal PhyWorkCorrect = new BigDecimal(0.00000);
//		BigDecimal MedFixedCorrectionScore = new BigDecimal(0.00000);
//		BigDecimal MedWorkCorrect = new BigDecimal(0.00000);

		BigDecimal PhyFixedCorrectionScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCorrectionScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal PhyWorkCorrect = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkCorrect).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal MedFixedCorrectionScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(1)).map(SumVo::getFixedCorrectionScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal MedWorkCorrect = kpiFixeds.stream().filter(s -> s.getJobType().equals(1)).map(SumVo::getWorkCorrect).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		byId.setPhyFixedCorrectionScoreSum(PhyFixedCorrectionScore);//医师固定绩效矫正之和A1
		byId.setPhyWorkCorrectSum(PhyWorkCorrect);//医师工作量绩效矫正之和A2
		byId.setMedFixedCorrectionScoreSum(MedFixedCorrectionScore);//医技固定绩效矫正之和B1
		byId.setMedWorkCorrectSum(MedWorkCorrect);//医技工作量绩效矫正之和B1

//		BigDecimal PhyFixedCountScore = new BigDecimal(0.00000);
//		BigDecimal PhyWorkSum = new BigDecimal(0.00000);
//		BigDecimal MedFixedCountScore = new BigDecimal(0.00000);
//		BigDecimal medWorkSum = new BigDecimal(0.00000);
		BigDecimal PhyFixedCountScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCountScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal PhyWorkSum = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkSum).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal MedFixedCountScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCountScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal medWorkSum = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkSum).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		byId.setPhyFixedUnit(PhyFixedCountScore.subtract(PhyFixedCorrectionScore));//医师固定绩每分绩效
		byId.setPhyWorkUnit(PhyWorkSum.subtract(PhyWorkCorrect));//医师工作量绩效每分绩效
		byId.setMedFixedUnit(MedFixedCountScore.subtract(MedFixedCorrectionScore));//医技固定量绩效每分绩效
		byId.setMedWorkUnit(medWorkSum.subtract(MedWorkCorrect));//医技工作量绩效每分绩效
		this.updateById(byId);
		return R.success("保存成功");
	}


	public void install(KpiAccounting param) {

		KpiAccounting byId = this.getById(param.getId());
		byId.setPerformanceSum(param.getPerformanceSum());//科室分配绩效总额

		String format = DateUtil.format(byId.getAttendanceMonth(), "yyyy-MM");
		List<SumVo> kpiFixeds = baseMapper.selectSumPhyMed(format);
		BigDecimal num1 = new BigDecimal(1.00);
		BigDecimal opsum = kpiFixeds.stream().map(SumVo::getKpiOpAllPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		byId.setOntherPerformanceSum(opsum);//其他绩效总额

		BigDecimal PerformanceSum = param.getPerformanceSum().subtract(opsum);
		byId.setPerformanceSum(PerformanceSum);//可分配绩效总额

		byId.setPhyCentum(param.getPhyCentum());//医师分配占比
		BigDecimal MedCentum = num1.subtract(param.getPhyCentum());
		byId.setMedCentum(MedCentum);//医技分配占比

		byId.setPhyFixedCoefficient(param.getPhyFixedCoefficient());//医师固定绩效分配系数
		BigDecimal PhyWorkCoefficient = num1.subtract(param.getPhyFixedCoefficient());
		byId.setPhyWorkCoefficient(PhyWorkCoefficient);//医师工作量绩效分配系数

		byId.setMedFixedCoefficient(param.getMedFixedCoefficient());//医技固定绩效分配系数
		BigDecimal WorkCoefficient = num1.subtract(param.getMedFixedCoefficient());
		byId.setMedWorkCoefficient(WorkCoefficient);//医技工作量绩效分配系数

		byId.setPhyFixedSum(PerformanceSum.multiply(param.getPhyCentum().multiply(param.getPhyFixedCoefficient())));//医师固定绩效总额
		byId.setPhyWorkSum(PerformanceSum.multiply(param.getPhyCentum().multiply(PhyWorkCoefficient)));//医师工作量绩效总额

		byId.setMedFixedSum(PerformanceSum.multiply(MedCentum.multiply(param.getMedFixedCoefficient())));
		byId.setMedWorkSum(PerformanceSum.multiply(MedCentum.multiply(WorkCoefficient)));



		BigDecimal PhyFixedCorrectionScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCorrectionScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal PhyWorkCorrect = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkCorrect).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal MedFixedCorrectionScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(1)).map(SumVo::getFixedCorrectionScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal MedWorkCorrect = kpiFixeds.stream().filter(s -> s.getJobType().equals(1)).map(SumVo::getWorkCorrect).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal PhyFixedCountScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCountScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal PhyWorkSum = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkSum).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal MedFixedCountScore = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getFixedCountScore).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal medWorkSum = kpiFixeds.stream().filter(s -> s.getJobType().equals(0)).map(SumVo::getWorkSum).reduce(BigDecimal.ZERO, BigDecimal::add)
			.divide(BigDecimal.valueOf(kpiFixeds.size()), 5, BigDecimal.ROUND_HALF_DOWN);

		byId.setPhyFixedCorrectionScoreSum(PhyFixedCorrectionScore);//医师固定绩效矫正之和A1
		byId.setPhyWorkCorrectSum(PhyWorkCorrect);//医师工作量绩效矫正之和A2
		byId.setMedFixedCorrectionScoreSum(MedFixedCorrectionScore);//医技固定绩效矫正之和B1
		byId.setMedWorkCorrectSum(MedWorkCorrect);//医技工作量绩效矫正之和B1

		byId.setPhyFixedUnit(PhyFixedCountScore.subtract(PhyFixedCorrectionScore));//医师固定绩每分绩效
		byId.setPhyWorkUnit(PhyWorkSum.subtract(PhyWorkCorrect));//医师工作量绩效每分绩效
		byId.setMedFixedUnit(MedFixedCountScore.subtract(MedFixedCorrectionScore));//医技固定量绩效每分绩效
		byId.setMedWorkUnit(medWorkSum.subtract(MedWorkCorrect));//医技工作量绩效每分绩效
		this.updateById(byId);
	}
}


