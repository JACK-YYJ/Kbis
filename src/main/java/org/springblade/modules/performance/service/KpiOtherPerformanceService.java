package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.performance.vo.KpiAttendanceDataVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/6 9:16
 */

public interface KpiOtherPerformanceService extends IService<KpiOtherPerformance> {


    List<KpiAttendanceDataVo> select();

	IPage<KpiAttendanceDataVo> seleckpitAttendancePage(IPage<Object> page, String toMonth, String idOrName);

}

