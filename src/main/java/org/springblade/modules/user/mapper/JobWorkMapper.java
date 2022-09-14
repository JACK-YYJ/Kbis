package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.modules.user.entity.JobWork;
import org.springblade.modules.user.vo.JobWorkVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/13 15:13
 */

@Mapper
public interface JobWorkMapper extends BaseMapper<JobWork> {
    List<JobWorkVo> selectJobSumList(Integer jId);
}
