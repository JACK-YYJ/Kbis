package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.vo.FixedToMonth;
import org.springblade.modules.performance.vo.PercentageVo;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Mapper
public interface KpiFixedMapper extends BaseMapper<KpiFixed> {
	IPage<KpiFixed> selectfixedPage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth);

	List<FixedToMonth> selectToMonth();

	IPage<KpiFixed> selectidOrName(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName, @Param("toMonth") String toMonth);

	PercentageVo selectPercentageVo(@Param("attendanceMonth") Date attendanceMonth, @Param("userCode") Integer userCode);

	List<KpiFixed> selectListifNUll(String format);

	FixedToMonth selectToMonthOne(Integer userCode);
}
