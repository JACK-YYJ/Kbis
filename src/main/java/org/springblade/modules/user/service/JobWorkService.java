package org.springblade.modules.user.service;

import org.springblade.modules.user.dto.UpdateJobDto;
import org.springblade.modules.user.entity.Job;
import org.springblade.modules.user.entity.JobWork;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.user.vo.JobWorkVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/26 19:15
 */

public interface JobWorkService extends IService<JobWork> {


	void add(UpdateJobDto param);

    List<JobWorkVo> selectJobSumList(Integer jId);
}


