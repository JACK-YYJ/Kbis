package org.springblade.modules.user.service;

import org.springblade.modules.user.entity.JobOtherP;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.user.vo.JobOtherPVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/29 11:30
 */

public interface JobOtherPService extends IService<JobOtherP>{
         List<JobOtherPVo> selectJobOtherP(Integer jId);
     }
