package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.performance.entity.KpiOtherPerformance;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 18:03
 */

@Mapper
public interface KpiOtherPerformanceMapper extends BaseMapper<KpiOtherPerformance> {
    Page<KpiOtherPerformance> selectOpattendancePage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth);

    IPage<KpiOtherPerformance> selectidOrName(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName, @Param("toMonth") String toMonth);

	List<KpiOtherPerformance> selectToMonth(String format);
}
