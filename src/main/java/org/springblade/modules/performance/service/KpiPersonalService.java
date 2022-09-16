package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiPersonal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.performance.vo.KpiAttendanceVo;
import org.springblade.modules.performance.vo.StatisticsVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

public interface KpiPersonalService extends IService<KpiPersonal> {

    IPage<KpiPersonal> selectPersonalPage(IPage<Object> page, String toMonth, String idOrName);


	List<StatisticsVo> selectStatisticsList( String toMonth);

}


