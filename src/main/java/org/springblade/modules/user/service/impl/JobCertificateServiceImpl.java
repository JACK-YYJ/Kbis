package org.springblade.modules.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.user.entity.Degree;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.JobCertificate;
import org.springblade.modules.user.mapper.JobCertificateMapper;
import org.springblade.modules.user.service.JobCertificateService;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:02
 */

@Service
public class JobCertificateServiceImpl extends ServiceImpl<JobCertificateMapper, JobCertificate> implements JobCertificateService {

    @Override
    public IPage<JobCertificate> selectJobCertificatePage(IPage<Object> page, String idOrName) {
        IPage<JobCertificate> pages = baseMapper.selectJobCertificatePage(page, idOrName);
        return pages;
    }
}





