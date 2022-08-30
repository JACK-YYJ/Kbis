package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.user.entity.JobOtherP;
import org.springblade.modules.user.vo.JobOtherPVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/29 11:30
 */

@Mapper
public interface JobOtherPMapper extends BaseMapper<JobOtherP> {
     List<JobOtherPVo> selectJobOtherP(Integer jId);

 }
