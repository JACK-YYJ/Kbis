package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.performance.entity.KpiAttendance;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.vo.UserVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.mapper.UserMapper;
import org.springblade.modules.user.service.UserService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<UserVo> selectUserPage(IPage<Object> page, UserDto param) {
        Page<UserVo> userVoPage = baseMapper.selectUserPage(page, param);
        return userVoPage;
    }

}




