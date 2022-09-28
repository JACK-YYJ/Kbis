package org.springblade.modules.performance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.dto.UpdateDto;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.mapper.KpiAttendanceMapper;
import org.springblade.modules.performance.service.KpiAttendanceService;

/**
 * @Author 元杰
 * @Date 2022/8/30 9:06
 */

@Service
public class KpiAttendanceServiceImpl extends ServiceImpl<KpiAttendanceMapper, KpiAttendance> implements KpiAttendanceService {
    @Autowired
    public UserService userService;

    @Override
    public IPage<KpiAttendanceVo> selectAttendancePage(IPage<Object> page, String toMonth, String idOrName) {
        Page<KpiAttendanceVo> kpiAttendancePage = baseMapper.selectAttendancePage(page, toMonth);
        List<KpiAttendanceVo> MonthIngList = baseMapper.selectByAdd();
        int count = userService.count();
		//判断当前 用户数据是不是 最新数据
//        if (!(MonthIngList.size()==count)){
//        	MonthIngList.forEach(s->{
//				baseMapper.deleteById(s.getId());
//			});
//        	userService.addIngMonthAttenddance();
//		}
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
        if (kpiAttendancePage.getRecords().size() == 0 && format.equals(toMonth)&&MonthIngList.size()==0) {
			userService.addIngMonthAttenddance();
        }
        if (idOrName != null) {
            IPage<KpiAttendanceVo> kpiAttendanceVoStream = baseMapper.selectidOrName(page, idOrName, toMonth);
            return kpiAttendanceVoStream;
        }
        return kpiAttendancePage;
    }

    @Override
    public List<KpiAttendance> selectAttendance(UpdateDto param) {
        List<KpiAttendance> list = baseMapper.selectAttendance(param);
        return list;
    }

	@Override
	public List<KpiAttendance> selectToMonth(String format) {
		List<KpiAttendance> list = baseMapper.selectToMonth(format);
    	return list;
	}
}



