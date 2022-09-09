package org.springblade.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.user.entity.Degree;
import org.springblade.modules.user.entity.JobCertificate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

public interface JobCertificateService extends IService<JobCertificate> {


    IPage<JobCertificate> selectJobCertificatePage(IPage<Object> page, String idOrName);

}






