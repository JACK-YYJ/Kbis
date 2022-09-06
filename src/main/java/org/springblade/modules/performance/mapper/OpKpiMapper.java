package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.performance.entity.OpKpi;import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/6 17:37
 */

@Mapper
public interface OpKpiMapper extends BaseMapper<OpKpi> {
    List<OpKpi> lambdaQuerydata(@Param("toMonth") String toMonth, @Param("idOrName") String idOrName);
}