package org.springblade.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springblade.auth.config.YmlConfig;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.dto.SysUserE;
import org.springblade.modules.user.entity.TechuserDict;
import org.springblade.modules.user.tool.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.system.mapper.SysUserMapper;
import org.springblade.modules.system.entity.SysUser;
import org.springblade.modules.system.service.SysUserService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:23
 */

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private YmlConfig ymlConfig;
	@Override
	public R enroll(SysUserE user) {
		SysUser byId = this.getById(user.getUserCode());
		if (ObjectUtils.isNotEmpty(byId)) {
			return R.success("该用户已存在");
		}
		SysUser sysUser = new SysUser();
		sysUser.setUserCode(user.getUserCode());
		sysUser.setUserPwd(ShiroUtils.enPas(user.getUserPwd(), String.valueOf(user.getUserCode())));
		sysUser.setCreateBy(user.getCreateBy());
		baseMapper.insert(sysUser);
		return R.success("注册成功");
	}

	@Override
	public void add(SysUser user) {
		SysUser sysUser = new SysUser();
		sysUser.setUserCode(user.getUserCode());
		sysUser.setCreateBy(user.getCreateBy());
		sysUser.setUserPwd(ShiroUtils.enPas(ymlConfig.getTheDefaultPassword(), String.valueOf(user.getUserCode())));
		baseMapper.insert(sysUser);
	}
}

