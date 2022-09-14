package org.springblade.modules.user.service.impl;

import org.springblade.modules.user.entity.Work;
import org.springblade.modules.user.service.WorkService;
import org.springblade.modules.user.vo.JobWorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.JobWork;
import org.springblade.modules.user.mapper.JobWorkMapper;
import org.springblade.modules.user.service.JobWorkService;

/**
 * @Author 元杰
 * @Date 2022/8/26 19:15
 */

@Service
public class JobWorkServiceImpl extends ServiceImpl<JobWorkMapper, JobWork> implements JobWorkService {


    @Autowired
    private WorkService workService;

    @Override
    public void add(Integer jId) {
        List<Work> list = workService.lambdaQuery().list();
        ArrayList<JobWork> jobWorkList1 = new ArrayList<>();
        for (Work work : list) {
            JobWork jobWork = new JobWork();
            jobWork.setWId(work.getWId());
            jobWork.setJId(jId);
            jobWorkList1.add(jobWork);
        }
        //工作类型
        this.saveBatch(jobWorkList1);
    }

    @Override
    public List<JobWorkVo> selectJobSumList(Integer jId) {

        return baseMapper.selectJobSumList(jId);
    }
}


