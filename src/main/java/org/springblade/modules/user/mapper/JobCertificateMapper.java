package org.springblade.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.springblade.modules.user.entity.JobCertificate;

/**
 * @Author 元杰
 * @Date 2022/9/8 16:01
 */

@Mapper
public interface JobCertificateMapper extends BaseMapper<JobCertificate> {
    IPage<JobCertificate> selectJobCertificatePage(@Param("page") IPage<Object> page, @Param("idOrName") String idOrName);
}