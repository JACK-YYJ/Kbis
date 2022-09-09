package org.springblade.modules.user.service;

import org.springblade.modules.user.entity.JobWork;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/26 19:15
 */

public interface JobWorkService extends IService<JobWork> {


    void add(Integer jId);

	List<JobWork> selectJobSumList(Integer jId);
}

