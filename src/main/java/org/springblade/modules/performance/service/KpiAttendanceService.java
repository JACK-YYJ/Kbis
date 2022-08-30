package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.performance.dto.UpdateDto;
import org.springblade.modules.performance.entity.KpiAttendance;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.performance.vo.KpiAttendanceVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/30 9:06
 */

public interface KpiAttendanceService extends IService<KpiAttendance> {
    IPage<KpiAttendanceVo> selectAttendancePage(IPage<Object> page, String toMonth);


	List<KpiAttendance> selectAttendance(UpdateDto param);

}


