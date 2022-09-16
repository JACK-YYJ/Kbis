package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.KpiPersonalVo;
import org.springblade.modules.performance.vo.StatisticsVo;
import org.springblade.modules.util.DateUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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

    @Override
    public IPage<KpiPersonal> selectPersonalPage(IPage<Object> page, String toMonth, String idOrName) {
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		Page<KpiPersonal> kpiAttendancePage = baseMapper.selectPersonalPage(page, toMonth);
		if (kpiAttendancePage.getRecords().size()==0&&format.equals(toMonth)){

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

				personal.setOpSum(s.getOpSum());
				personal.setFixedSum(s.getFixedSum());
				personal.setWorkSum(s.getWorkSum());
				personal.setPersonalSum(s.getFixedSum()
					.add(s.getFixedSum()
						.add(s.getWorkSum())
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
	public List<StatisticsVo> selectStatisticsList( String toMonth) {
		List<StatisticsVo> selectByjobName = baseMapper.selectByjobName(toMonth);
		List<StatisticsVo> selectByjobtype = baseMapper.selectByjobtype(toMonth);
		ArrayList<StatisticsVo> objects = new ArrayList<>();
		objects.addAll(selectByjobName);
		objects.addAll(selectByjobtype);

		return objects;
	}


}


