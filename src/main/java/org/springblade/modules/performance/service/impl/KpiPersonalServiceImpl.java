package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.mapper.KpiAccountingMapper;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.KpiPersonalVo;
import org.springblade.modules.performance.vo.PercentageSelectVo;
import org.springblade.modules.performance.vo.StatisticsVo;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.entity.KpiPersonal;
import org.springblade.modules.performance.mapper.KpiPersonalMapper;
import org.springblade.modules.performance.service.KpiPersonalService;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Service
public class KpiPersonalServiceImpl extends ServiceImpl<KpiPersonalMapper, KpiPersonal> implements KpiPersonalService {
	@Autowired
	private KpiAccountingMapper kpiAccountingMapper;
    @Override
    public IPage<PercentageSelectVo> selectPersonalPage(IPage<Object> page, String toMonth, String idOrName) {
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		Page<PercentageSelectVo> kpiAttendancePage = baseMapper.selectPersonalPage(page, toMonth);
		if (kpiAttendancePage.getRecords().size() ==0 && format.equals(toMonth)){

			KpiAccounting kpiAccounting = kpiAccountingMapper.selectByToMonth(toMonth);

			List<KpiPersonalVo> MonthIngList = baseMapper.selectByAdd(toMonth);
			MonthIngList.forEach(s->{
				KpiPersonal personal = new KpiPersonal();
				personal.setUId(s.getUId());
				personal.setUserCode(s.getUserCode());
				personal.setUserName(s.getUserName());
				personal.setAttendanceMonth(s.getAttendanceMonth());
				personal.setPercentage(s.getPercentage());

				personal.setJobType(s.getJobType());
				personal.setJobName(s.getJobName());
				personal.setJobRatio(s.getJobRatio());

				personal.setCardId(s.getCardId());
				//岗位联调
				if (!s.getButtonOther()){
					personal.setOpSum(BigDecimal.valueOf(0));
				}else {
					personal.setOpSum(s.getOpSum());//	其他绩效
				}
				//实习生 公式
				if (s.getJobGs()==3){
					personal.setFixedSum(BigDecimal.valueOf(0));
					personal.setWorkSum(BigDecimal.valueOf(0));
				}
				if (s.getJobType().equals(0)){
					if(!s.getButtonFixed()){
						personal.setFixedSum(BigDecimal.valueOf(0));
					}else {
						personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getPhyFixedUnit()));//	重新计算
					}
					if(!s.getButtonWorkload()){
						personal.setWorkSum(BigDecimal.valueOf(0));
					}else{
						personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getPhyWorkUnit()));//	重新计算
					}

				}
				if (s.getJobType().equals(1)){
					if(!s.getButtonFixed()){
						personal.setFixedSum(BigDecimal.valueOf(0));
					}else {
						personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getMedFixedUnit()));//	重新计算
					}
					if(!s.getButtonWorkload()){
						personal.setWorkSum(BigDecimal.valueOf(0));
					}else{
						personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getMedWorkUnit()));//	重新计算
					}
				}

				personal.setPersonalSum(
					personal.getOpSum()
					.add(personal.getFixedSum()
						.add(personal.getWorkSum())
					)
				);
				this.save(personal);
			});
		}
		//如果不是当前月份 的话
		if (kpiAttendancePage.getRecords().size() ==0 && (!format.equals(toMonth))){

			KpiAccounting kpiAccounting = kpiAccountingMapper.selectByToMonth(toMonth);

			List<KpiPersonalVo> MonthIngList = baseMapper.selectByAdd(toMonth);
			MonthIngList.forEach(s->{
				KpiPersonal personal = new KpiPersonal();
				personal.setUId(s.getUId());
				personal.setUserCode(s.getUserCode());
				personal.setUserName(s.getUserName());
				personal.setAttendanceMonth(s.getAttendanceMonth());
				personal.setPercentage(s.getPercentage());

				personal.setJobType(s.getJobType());
				personal.setJobName(s.getJobName());
				personal.setJobRatio(s.getJobRatio());

				personal.setCardId(s.getCardId());
				//岗位联调
				if (!s.getButtonOther()){
					personal.setOpSum(BigDecimal.valueOf(0));
				}else {
					personal.setOpSum(s.getOpSum());//	其他绩效
				}
				//实习生 公式
				if (s.getJobGs()==3){
					personal.setFixedSum(BigDecimal.valueOf(0));
					personal.setWorkSum(BigDecimal.valueOf(0));
				}
				if (s.getJobType().equals(0)){
					if(!s.getButtonFixed()){
						personal.setFixedSum(BigDecimal.valueOf(0));
					}else {
						personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getPhyFixedUnit()));//	重新计算
					}
					if(!s.getButtonWorkload()){
						personal.setWorkSum(BigDecimal.valueOf(0));
					}else{
						personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getPhyWorkUnit()));//	重新计算
					}

				}
				if (s.getJobType().equals(1)){
					if(!s.getButtonFixed()){
						personal.setFixedSum(BigDecimal.valueOf(0));
					}else {
						personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getMedFixedUnit()));//	重新计算
					}
					if(!s.getButtonWorkload()){
						personal.setWorkSum(BigDecimal.valueOf(0));
					}else{
						personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getMedWorkUnit()));//	重新计算
					}
				}

				personal.setPersonalSum(
					personal.getOpSum()
						.add(personal.getFixedSum()
							.add(personal.getWorkSum())
						)
				);
				this.save(personal);
			});
		}
		if (idOrName != null) {
			IPage<PercentageSelectVo> kpiPersonalIPageByIdOrName = baseMapper.selectidOrName(page, idOrName, toMonth);
			return kpiPersonalIPageByIdOrName;
		}
		return kpiAttendancePage;

    }

	@Override
	public List<StatisticsVo> selectStatisticsList(String toMonth) {
		List<StatisticsVo> selectByjobName = baseMapper.selectByjobName(toMonth);
		List<StatisticsVo> selectByjobtype = baseMapper.selectByjobtype(toMonth);
		ArrayList<StatisticsVo> objects = new ArrayList<>();
		objects.addAll(selectByjobName);
		objects.addAll(selectByjobtype);

		return objects;
	}

	@Override
	public void deleteBysaveAccounting() {
//		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		baseMapper.deleteBysaveAccounting();

	}

	@Override
	public void updateByPersonal(String toMonth) {
		KpiAccounting kpiAccounting = kpiAccountingMapper.selectByToMonth(toMonth);

		List<KpiPersonalVo> MonthIngList = baseMapper.selectByMonth(toMonth);
		MonthIngList.forEach(s->{

			KpiPersonal one = this.getOne(new QueryWrapper<KpiPersonal>()
				.eq(KpiPersonal.COL_U_ID, s.getUId())
				.like(KpiPersonal.COL_ATTENDANCE_MONTH, DateUtil.format(s.getAttendanceMonth(),"yyyy-MM")));

			one.setUserCode(s.getUserCode());
			one.setUserName(s.getUserName());
			one.setPercentage(s.getPercentage());

			one.setJobType(s.getJobType());
			one.setJobName(s.getJobName());
			one.setJobRatio(s.getJobRatio());

			one.setCardId(s.getCardId());
			if (!s.getButtonOther()){
				one.setOpSum(BigDecimal.valueOf(0));
			}else {
				one.setOpSum(s.getOpSum());//	其他绩效
			}

			//实习生 公式
			if (s.getJobGs()==3){
				one.setFixedSum(BigDecimal.valueOf(0));
				one.setWorkSum(BigDecimal.valueOf(0));
			}
			if (s.getJobType()==0){
				if(!s.getButtonFixed()){
					one.setFixedSum(BigDecimal.valueOf(0));
				}else {
					one.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getPhyFixedUnit()));//	重新计算
				}
				if(!s.getButtonWorkload()){
					one.setWorkSum(BigDecimal.valueOf(0));
				}else{
					one.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getPhyWorkUnit()));//	重新计算
				}
			}
			if (s.getJobType()==1){
				if(!s.getButtonFixed()){
					one.setFixedSum(BigDecimal.valueOf(0));
				}else {
					one.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getMedFixedUnit()));//	重新计算
				}
				if(!s.getButtonWorkload()){
					one.setWorkSum(BigDecimal.valueOf(0));
				}else{
					one.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getMedWorkUnit()));//	重新计算
				}
			}
			one.setPersonalSum(
				one.getOpSum()
					.add(one.getFixedSum()
						.add(one.getWorkSum())
					)
			);
			this.updateById(one);
		});
	}

}


