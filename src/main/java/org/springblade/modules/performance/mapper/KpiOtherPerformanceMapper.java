package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.performance.entity.KpiOtherPerformance;
import org.springblade.modules.performance.vo.KpiAttendanceDataVo;
import org.springblade.modules.performance.vo.KpiAttendanceVo;

/**
 * @Author 元杰
 * @Date 2022/9/6 10:03
 */

@Mapper
public interface KpiOtherPerformanceMapper extends BaseMapper<KpiOtherPerformance> {
	Page<KpiOtherPerformance> selectkpiAttendancePage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth, @Param("idOrName") String idOrName);
}
