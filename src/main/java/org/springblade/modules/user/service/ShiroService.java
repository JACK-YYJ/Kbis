package org.springblade.modules.user.service;

import org.springblade.modules.system.entity.SysToken;
import org.springblade.modules.user.entity.TechuserToken;

import java.util.Map;

/**
 * @ClassName ShiroService
 * @Author Ja'c'k
 * @Dato 2022/6/14 14:05
 * @Version 1.0
 */
public interface ShiroService {

	/**
	 * create token by userId
	 * @param userId
	 * @return
	 */
	String createToken(Integer userId);

	/**
	 * logout
	 * @param userId
	 */
	void logout(Integer userId);

	/**
	 * find token by token
	 * @param accessToken
	 * @return
	 */
	SysToken findByToken(String accessToken);
}
