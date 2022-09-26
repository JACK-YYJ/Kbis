package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiAccounting;
import org.springblade.modules.performance.mapper.KpiAccountingMapper;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.KpiPersonalVo;
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
    public IPage<KpiPersonal> selectPersonalPage(IPage<Object> page, String toMonth, String idOrName) {
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		Page<KpiPersonal> kpiAttendancePage = baseMapper.selectPersonalPage(page, toMonth);
		if (kpiAttendancePage.getRecords().size() ==0 && format.equals(toMonth)){

			KpiAccounting kpiAccounting = kpiAccountingMapper.selectByToMonth(format);

			List<KpiPersonalVo> MonthIngList = baseMapper.selectByAdd(format);
			MonthIngList.forEach(s->{
				KpiPersonal personal = new KpiPersonal();
				personal.setUserCode(s.getUserCode());
				personal.setUserName(s.getUserName());
				personal.setAttendanceMonth(s.getAttendanceMonth());
				personal.setPercentage(s.getPercentage());

				personal.setJobType(s.getJobType());
				personal.setJobName(s.getJobName());
				personal.setJobRatio(s.getJobRatio());

				personal.setCardId(s.getCardId());
				personal.setOpSum(s.getOpSum());//	其他绩效
				//实习生 公式
				if (s.getJobGs()==3){
					personal.setFixedSum(BigDecimal.valueOf(0));
					personal.setWorkSum(BigDecimal.valueOf(0));
				}
				if (s.getJobType().equals(0)){
					personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getPhyFixedUnit()));//	重新计算
					personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getPhyWorkUnit()));//	重新计算
				}
				if (s.getJobType().equals(1)){
					personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getMedFixedUnit()));//	重新计算
					personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getMedWorkUnit()));//	重新计算
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
			IPage<KpiPersonal> kpiPersonalIPageByIdOrName = baseMapper.selectidOrName(page, idOrName, toMonth);
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
	public void deleteBysaveAccounting(String toMonth) {
//		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		baseMapper.deleteBysaveAccounting(toMonth);

	}

	@Override
	public void add(String toMonth) {
		KpiAccounting kpiAccounting = kpiAccountingMapper.selectByToMonth(toMonth);

		List<KpiPersonalVo> MonthIngList = baseMapper.selectByAdd(toMonth);
		MonthIngList.forEach(s->{
			KpiPersonal personal = new KpiPersonal();
			personal.setUserCode(s.getUserCode());
			personal.setUserName(s.getUserName());
			personal.setAttendanceMonth(s.getAttendanceMonth());
			personal.setPercentage(s.getPercentage());

			personal.setJobType(s.getJobType());
			personal.setJobName(s.getJobName());
			personal.setJobRatio(s.getJobRatio());

			personal.setCardId(s.getCardId());
			personal.setOpSum(s.getOpSum());//	其他绩效
			//实习生 公式
			if (s.getJobGs()==3){
				personal.setFixedSum(BigDecimal.valueOf(0));
				personal.setWorkSum(BigDecimal.valueOf(0));
			}
			if (s.getJobType()==0){
				personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getPhyFixedUnit()));//	重新计算
				personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getPhyWorkUnit()));//	重新计算
			}
			if (s.getJobType()==1){
				personal.setFixedSum(s.getFixedSum().multiply(kpiAccounting.getMedFixedUnit()));//	重新计算
				personal.setWorkSum(s.getWorkSum().multiply(kpiAccounting.getMedWorkUnit()));//	重新计算
			}

			personal.setPersonalSum(
				personal.getOpSum()
					.add(personal.getFixedSum()
						.add(personal.getWorkSum())
					)
			);
			this.updateById(personal);
		});
	}

}


