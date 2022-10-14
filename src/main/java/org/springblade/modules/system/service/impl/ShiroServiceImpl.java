package org.springblade.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.auth.TokenGenerator;
import org.springblade.modules.system.entity.SysToken;
import org.springblade.modules.system.mapper.SysTokenMapper;
import org.springblade.modules.user.service.ShiroService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroServiceImpl
 * @Author Ja'c'k
 * @Dato 2022/6/14 14:07
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl extends ServiceImpl<SysTokenMapper, SysToken> implements ShiroService {
	//12小时后失效
	private final static int EXPIRE = 12;

	/**
	 * create token by userId
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public String createToken(Integer userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		//当前时间
		LocalDateTime now = LocalDateTime.now();
		//过期时间
		LocalDateTime expireTime = now.plusHours(EXPIRE);
		//判断是否生成过token
		SysToken tokenEntity = this.baseMapper.findByUserId(userId);
		if (tokenEntity == null) {
			tokenEntity = new SysToken();
			tokenEntity.setUserCode(userId);
			//保存token
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			this.baseMapper.insert(tokenEntity);
		} else {
			//更新token
			tokenEntity.setUserCode(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			this.baseMapper.updateById(tokenEntity);
		}
		;
//		result.put("expire", expireTime);
		return token;
	}


	/**
	 *更新数据库的token，使前端拥有的token失效
	 *       防止黑客利用token搞事情
	 *
	 * @param userId
	 */
	@Override
	public void logout(Integer userId) {
		this.baseMapper.deleteById(userId);
	}

	/**
	 * find token by token
	 *
	 * @param accessToken
	 * @return
	 */
	@Override
	public SysToken findByToken(String accessToken) {
		return baseMapper.findByToken(accessToken);
	}
}
