package org.springblade.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.user.vo.UserVo;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

public interface UserService extends IService<User> {


    IPage<UserVo> selectUserPage(IPage<Object> page, UserDto param);


}





