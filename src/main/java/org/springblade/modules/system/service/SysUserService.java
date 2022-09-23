package org.springblade.modules.system.service;

import org.springblade.core.tool.api.R;
import org.springblade.modules.system.dto.SysUserE;
import org.springblade.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:23
 */

public interface SysUserService extends IService<SysUser> {


    R enroll(SysUserE user);


    void add(SysUser user);
}

