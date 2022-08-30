package org.springblade.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springblade.modules.system.entity.SysToken;
import org.springblade.modules.user.entity.TechuserToken;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:24
 */

@Mapper
public interface SysTokenMapper extends BaseMapper<SysToken> {
	/**
	 * 通过userID查找
	 *
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM sys_token  WHERE  user_code =#{userId}")
	SysToken findByUserId(Integer userId);
	/**
	 * 通过token查找
	 *
	 * @param accessToken
	 * @return
	 */
	@Select("SELECT * FROM sys_token WHERE  token =#{accessToken}")
	SysToken findByToken(String accessToken);
}
