package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.user.entity.Position;

/**
 * @Author 元杰
 * @Date 2022/8/26 18:21
 */

@Mapper
public interface PositionMapper extends BaseMapper<Position> {
	IPage<Position> selectPositionPage(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName);
}
