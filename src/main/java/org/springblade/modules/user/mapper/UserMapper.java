package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.user.dto.UserDto;import org.springblade.modules.user.entity.User;import org.springblade.modules.user.vo.UserVo;

/**
 * @Author 元杰
 * @Date 2022/8/26 12:48
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<UserVo> selectUserPage(@Param("page") IPage<Object> page, @Param("param") UserDto param);
}