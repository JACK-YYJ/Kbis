package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.user.entity.OtherPerformance;

/**
 * @Author 元杰
 * @Date 2022/9/8 16:01
 */

@Mapper
public interface OtherPerformanceMapper extends BaseMapper<OtherPerformance> {
    IPage<OtherPerformance> selectOtherPerformancePage(@Param("page") IPage<Object> page, @Param("otherPerformanceName") String otherPerformanceName);
}