package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.user.entity.Job;

/**
 * @Author 元杰
 * @Date 2022/9/19 16:08
 */

@Mapper
public interface JobMapper extends BaseMapper<Job> {
    IPage<Job> selectJobPage(IPage<Object> page, @Param("jobName") String jobName);
}