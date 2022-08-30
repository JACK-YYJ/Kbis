package org.springblade.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.user.entity.Degree;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.user.vo.UserVo;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

public interface DegreeService extends IService<Degree> {


    IPage<Degree> selectDegreePage(IPage<Object> page, String idOrName);

}

