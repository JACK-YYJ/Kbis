package org.springblade.modules.user.service.impl;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.mapper.JobMapper;
import org.springblade.modules.user.entity.Job;
import org.springblade.modules.user.service.JobService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Override
    public IPage<Job> selectJobPage(IPage<Object> page, String jobName) {
        IPage<Job> pages = baseMapper.selectJobPage(page, jobName);
        System.out.println(pages);
        return pages;
    }
}



