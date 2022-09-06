package org.springblade.modules.performance.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.service.OpKpiService;
import org.springblade.modules.performance.vo.KpiAttendanceDataVo;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.KpiBtVO;
import org.springblade.modules.user.entity.OtherPerformance;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.OtherPerformanceService;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.mapper.KpiOtherPerformanceMapper;
import org.springblade.modules.performance.service.KpiOtherPerformanceService;

/**
 * @Author 元杰
 * @Date 2022/9/6 9:16
 */

@Service
public class KpiOtherPerformanceServiceImpl extends ServiceImpl<KpiOtherPerformanceMapper, KpiOtherPerformance> implements KpiOtherPerformanceService {

	@Autowired
	public UserService userService;


	@Autowired
	private OtherPerformanceService otherPerformanceService;
	@Autowired
	private OpKpiService opKpiService;
	@Override
	public List<KpiAttendanceDataVo> select() {
		return null;
	}

	@Override
	public IPage<KpiAttendanceDataVo> seleckpitAttendancePage(IPage<Object> page, String toMonth, String idOrName) {

		Page<KpiAttendanceDataVo> AllList = new Page<>();
		//筛选 前三列数据
		Page<KpiOtherPerformance> kpiAttendancePage = baseMapper.selectkpiAttendancePage(page, toMonth,idOrName);


		if (kpiAttendancePage.getRecords().size()==0) {
			List<User> userList = userService.lambdaQuery().list();
			ArrayList<KpiOtherPerformance> kpiop = new ArrayList<>();
			for (User user : userList) {
				KpiOtherPerformance s = new KpiOtherPerformance();
				s.setUserCode(String.valueOf(user.getUserCode()));
				s.setUserName(user.getUserName());
				s.setAttendanceMonth(DateUtils.getTodayDateMonth());
				s.setKopId((user.getUserCode())+"-"+DateUtils.getTodayDateMonth());
				kpiop.add(s);
				//
				List<OtherPerformance> query = otherPerformanceService.query().orderByAsc("op_id").list();
				query.forEach(s2->{
					OpKpi opKpi = new OpKpi();
					opKpi.setOpId(s2.getOpId());
					opKpi.setKopId((user.getUserCode())+"-"+DateUtils.getTodayDateMonth());
					opKpiService.save(opKpi);
				});
			}
			this.saveBatch(kpiop);
		}
		//中间表  过滤后的当前月份（可过滤筛选）
		List<OpKpi> lambdaQueryOpKpi = opKpiService.lambdaQuerydata(toMonth,idOrName);
		List<OtherPerformance> otherPerformanceList= otherPerformanceService.query().orderByAsc("op_id").list();

		kpiAttendancePage.getRecords().forEach(s->{
			KpiAttendanceDataVo kpiAttendanceDataVo = new KpiAttendanceDataVo();
			kpiAttendanceDataVo.setUserCode(s.getUserCode());
			kpiAttendanceDataVo.setKpiOpId(s.getKpiOpId());
			kpiAttendanceDataVo.setUserName(s.getUserName());
			kpiAttendanceDataVo.setAttendanceMonth(s.getAttendanceMonth());
			HashMap<String, String> btVOmap = new HashMap<>();
			for (int i = 0; i < otherPerformanceList.size(); i++) {
				lambdaQueryOpKpi.stream().filter(s1->s.getUserName().equals(s.getUserCode().))//用户 string
				btVOmap.put("Other"+(i+1),);
			}
			kpiAttendanceDataVo.setKpiOpList();
		});

//		if(idOrName!=null){
//			IPage<KpiAttendanceVo> kpiAttendanceVoStream = baseMapper.selectidOrName(page,idOrName,toMonth);
//			return null;
//		}
		return AllList;
	}
}

