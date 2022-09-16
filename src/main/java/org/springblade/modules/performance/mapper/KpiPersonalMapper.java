package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.performance.entity.KpiPersonal;
import org.springblade.modules.performance.vo.KpiPersonalVo;
import org.springblade.modules.performance.vo.StatisticsVo;

import java.util.Date;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/15 16:21
 */

@Mapper
public interface KpiPersonalMapper extends BaseMapper<KpiPersonal> {
	Page<KpiPersonal> selectPersonalPage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth);


	List<KpiPersonalVo> selectByAdd(@Param("format") String format);

	IPage<KpiPersonal> selectidOrName(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName, @Param("toMonth") String toMonth);

	List<StatisticsVo> selectByjobName(@Param("toMonth") String toMonth);

	List<StatisticsVo> selectByjobtype(@Param("toMonth") String toMonth);
}
