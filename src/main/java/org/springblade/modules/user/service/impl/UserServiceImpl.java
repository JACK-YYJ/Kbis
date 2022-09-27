package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.service.KpiAttendanceService;
import org.springblade.modules.performance.service.KpiFixedService;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.vo.UserVo;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    @Override
    public IPage<UserVo> selectUserPage(IPage<Object> page, UserDto param) {
        Page<UserVo> userVoPage = baseMapper.selectUserPage(page, param);
        return userVoPage;
    }

	@Override
	public void addIngMonthAttenddance() {
		List<User> userList = this.lambdaQuery().orderByDesc(User::getCreateTime).list();
		ArrayList<KpiAttendance> kpiAttendances = new ArrayList<>();
		for (User user : userList) {
			KpiAttendance kpiAttendance = new KpiAttendance();
			kpiAttendance.setUserCode(user.getUserCode());
			kpiAttendance.setUserName(user.getUserName());
			kpiAttendance.setAttendanceMonth(DateUtils.getNowDate());
			kpiAttendance.setAttendanceState(1);
			kpiAttendance.setAttendanceDay(23);
			kpiAttendance.setMonthDay(23);
			kpiAttendances.add(kpiAttendance);
		}
		kpiAttendanceService.saveBatch(kpiAttendances);
	}

	@Override
	public void addCompute(User param) {
		KpiAttendance kpiAttendance = new KpiAttendance();
		kpiAttendance.setUserCode(param.getUserCode());
		kpiAttendance.setUserName(param.getUserName());
		kpiAttendance.setAttendanceMonth(DateUtils.getNowDate());
		kpiAttendance.setAttendanceState(1);
		kpiAttendance.setAttendanceDay(23);
		kpiAttendance.setMonthDay(23);
		kpiAttendanceService.save(kpiAttendance);
	}

}





