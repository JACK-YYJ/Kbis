package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.user.entity.Work;

/**
 * @Author 元杰
 * @Date 2022/9/8 16:01
 */

@Mapper
public interface WorkMapper extends BaseMapper<Work> {
    IPage<Work> selectWorkPage(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName);
}