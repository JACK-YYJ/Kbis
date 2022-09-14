package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.vo.WorkSumByUserCode;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/13 11:45
 */

@Mapper
public interface KpiWorkloadMapper extends BaseMapper<KpiWorkload> {
	Page<KpiWorkload> kpiWorkloadPage(@Param("page") IPage<Object> page, @Param("toMonth") String toMonth);

	IPage<KpiWorkload> selectidOrName(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName, @Param("toMonth") String toMonth);

	WorkSumByUserCode setectWorkSumByUserCode(@Param("userCode") Integer userCode, @Param("i") int i);
}
