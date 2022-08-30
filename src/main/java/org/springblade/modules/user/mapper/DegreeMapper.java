package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springblade.modules.user.entity.Degree;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:25
 */

@Mapper
public interface DegreeMapper extends BaseMapper<Degree> {

	IPage<Degree> selectDegreePage(@Param("page") IPage<Object> page, @Param("degreeName") String idOrName);
}
