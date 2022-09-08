package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.Work;
import org.springblade.modules.user.mapper.WorkMapper;
import org.springblade.modules.user.service.WorkService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {

    @Override
    public IPage<Work> selectWorkPage(IPage<Object> page, String idOrName) {
        IPage<Work> pages = baseMapper.selectWorkPage(page, idOrName);
        return pages;
    }
}





