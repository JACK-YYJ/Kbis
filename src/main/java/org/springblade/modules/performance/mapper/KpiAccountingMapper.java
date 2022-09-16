package org.springblade.modules.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.performance.entity.KpiAccounting;import org.springblade.modules.performance.vo.SumVo;import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/15 14:53
 */

@Mapper
public interface KpiAccountingMapper extends BaseMapper<KpiAccounting> {
    List<SumVo> selectSumPhyMed(@Param("toMonth") String toMonth);

    KpiAccounting selectByToMonth(@Param("toMonth") String toMonth);
}