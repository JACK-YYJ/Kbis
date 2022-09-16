package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.performance.dto.UpdateDto;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.performance.vo.KpiAttendanceVo;

import java.util.Date;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Mapper
public interface KpiAttendanceMapper extends BaseMapper<KpiAttendance> {
	Page<KpiAttendanceVo> selectAttendancePage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth);

	List<KpiAttendance> selectAttendance(@Param("param") UpdateDto param);

	Page<KpiAttendanceVo> selectidOrName(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName, @Param("toMonth") String toMonth);

	List<KpiAttendanceVo> selectByAdd(@Param("nowDate") Date nowDate);
}
