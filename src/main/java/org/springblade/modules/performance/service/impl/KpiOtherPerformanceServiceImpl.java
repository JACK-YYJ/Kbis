package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.service.KpiAttendanceService;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private UserService userService;
	@Autowired
	private OtherPerformanceService otherPerformanceService;
	@Autowired
	private KpiAttendanceService kpiAttendanceService;

    @Override
    public IPage<KpiOtherPerformance> selectOpattendancePage(IPage<Object> page, String toMonth, String idOrName) {
        Page<KpiOtherPerformance> allList = baseMapper.selectOpattendancePage(page, toMonth);
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
        if (allList.getRecords().size() == 0&&format.equals(toMonth)) {
            List<User> userList = userService.lambdaQuery().orderByAsc(User::getUId).list();
            ArrayList<KpiOtherPerformance> kpiopaddList = new ArrayList<>();
            for (User user : userList) {
                KpiOtherPerformance addKpiOp = new KpiOtherPerformance();
                addKpiOp.setUId(user.getUId());
                addKpiOp.setUserCode(user.getUserCode());
                addKpiOp.setUserName(user.getUserName());
                addKpiOp.setAttendanceMonth(DateUtils.getNowDate());
                kpiopaddList.add(addKpiOp);
            }
            this.saveBatch(kpiopaddList);
        }
        if (idOrName != null) {
            IPage<KpiOtherPerformance> kpiOpidOrNameList = baseMapper.selectidOrName(page, idOrName, toMonth);
            return kpiOpidOrNameList;
        }
        return allList;
    }

	/**
	 *
	 * @param param
	 */
	@Override
	public void updateByAllPrice(KpiOtherPerformance param) {
		KpiOtherPerformance kpiOp = this.getById(param.getKpiOpId());
		kpiOp.setManagePrice(param.getManagePrice());
		kpiOp.setManagePerformancePrice(param.getManagePerformancePrice());
		kpiOp.setVariousRewardsPrice(param.getVariousRewardsPrice());
		kpiOp.setVariousAssessmentsPrice(param.getVariousAssessmentsPrice());
		kpiOp.setVariousSubsidiesPrice(param.getVariousSubsidiesPrice());

		kpiOp.setOtherNightShiftsSum(param.getOtherNightShiftsSum());
		kpiOp.setCtNightShiftsSum(param.getCtNightShiftsSum());
		kpiOp.setOvertimeSum(param.getOvertimeSum());
		kpiOp.setBedsideSum(param.getBedsideSum());

		kpiOp.setRadiationPrice(param.getRadiationPrice());
		BigDecimal i = param.getManagePrice()
			.add(param.getManagePerformancePrice()
				.add(param.getVariousRewardsPrice()
					.add(param.getVariousAssessmentsPrice()
						.add(param.getVariousSubsidiesPrice()
							.add(param.getRadiationPrice()) ))
				));
		// 其他绩效 里面的单价数量
		OtherPerformance oNSSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "OtherNightShiftsSum"));
		OtherPerformance ctNSSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "CtNightShiftsSum"));
		OtherPerformance oSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "OvertimeSum"));
		OtherPerformance bSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "BedsideSum"));
		//合计
		BigDecimal j = i.add(param.getOtherNightShiftsSum().multiply(oNSSum.getOpSum())
			.add(param.getCtNightShiftsSum().multiply(ctNSSum.getOpSum())
				.add(param.getOvertimeSum().multiply(oSum.getOpSum())
					.add(param.getBedsideSum().multiply(bSum.getOpSum()))
				)
			)
		);
		kpiOp.setKpiOpAllPrice(j);
		this.updateById(kpiOp);
	}

	/**
	 * 计算一下
	 * @param paramList
	 */
	@Override
	public void updateByAllCompute(List<KpiOtherPerformance> paramList) {
		paramList.forEach(param->{

			KpiOtherPerformance kpiOp = this.getById(param.getKpiOpId());
			kpiOp.setManagePrice(param.getManagePrice());
			kpiOp.setManagePerformancePrice(param.getManagePerformancePrice());
			kpiOp.setVariousRewardsPrice(param.getVariousRewardsPrice());
			kpiOp.setVariousAssessmentsPrice(param.getVariousAssessmentsPrice());
			kpiOp.setVariousSubsidiesPrice(param.getVariousSubsidiesPrice());

			kpiOp.setOtherNightShiftsSum(param.getOtherNightShiftsSum());
			kpiOp.setCtNightShiftsSum(param.getCtNightShiftsSum());
			kpiOp.setOvertimeSum(param.getOvertimeSum());
			kpiOp.setBedsideSum(param.getBedsideSum());

			kpiOp.setRadiationPrice(param.getRadiationPrice());
			BigDecimal i = param.getManagePrice()
				.add(param.getManagePerformancePrice()
					.add(param.getVariousRewardsPrice()
						.add(param.getVariousAssessmentsPrice()
							.add(param.getVariousSubsidiesPrice()
								.add(param.getRadiationPrice()) ))
					));
			// 其他绩效 里面的单价数量
			OtherPerformance oNSSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "OtherNightShiftsSum"));
			OtherPerformance ctNSSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "CtNightShiftsSum"));
			OtherPerformance oSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "OvertimeSum"));
			OtherPerformance bSum = otherPerformanceService.getOne(new QueryWrapper<OtherPerformance>().eq(OtherPerformance.COL_OP_BT_NAME, "BedsideSum"));
			System.out.println(DateUtil.format(param.getAttendanceMonth(),"yyyy-MM"));

			LambdaQueryWrapper<KpiAttendance> wrapper = new LambdaQueryWrapper<>();
			wrapper.eq(KpiAttendance::getAttendanceMonth,param.getAttendanceMonth());
			wrapper.eq(KpiAttendance::getUserCode,param.getUserCode());


			KpiAttendance one = kpiAttendanceService.getOne(new QueryWrapper<KpiAttendance>()
				.eq(KpiAttendance.COL_USER_CODE, param.getUserCode())
				.like((KpiAttendance.COL_ATTENDANCE_MONTH),String.valueOf(DateUtil.format(param.getAttendanceMonth(),"yyyy-MM")))
			);

//			KpiAttendance one1 = kpiAttendanceService.lambdaQuery().eq(KpiAttendance::getUserCode,param.getUserCode()).eq(KpiAttendance::getAttendanceMonth,param.getAttendanceMonth()).list().get(0);
			//合计
			BigDecimal j = i.add(param.getOtherNightShiftsSum().multiply(oNSSum.getOpSum())
				.add(param.getCtNightShiftsSum().multiply(ctNSSum.getOpSum())
					.add(param.getOvertimeSum().multiply(oSum.getOpSum())
						.add(param.getBedsideSum().multiply(bSum.getOpSum()))
					)
				)
			);
			kpiOp.setComputeStatus(1);
			//合计  借调
			if(one.getAttendanceState()==2){
				kpiOp.setKpiOpAllPrice(BigDecimal.valueOf(0));
			}else {
				kpiOp.setKpiOpAllPrice(j);
			}
			this.updateById(kpiOp);
		});
	}

	@Override
	public List<KpiOtherPerformance> selectToMonth(String format) {
		List<KpiOtherPerformance> kpiOtherPerformances = baseMapper.selectToMonth(format);
		return kpiOtherPerformances;
	}
}






