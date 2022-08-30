package org.springblade.modules.user.service.impl;

import org.springblade.modules.user.vo.JobOtherPVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.mapper.JobOtherPMapper;
import org.springblade.modules.user.entity.JobOtherP;
import org.springblade.modules.user.service.JobOtherPService;
 /**
 * @Author 元杰
 * @Date 2022/8/29 11:30
 */

@Service
public class JobOtherPServiceImpl extends ServiceImpl<JobOtherPMapper, JobOtherP> implements JobOtherPService{

	 @Override
	 public List<JobOtherPVo> selectJobOtherP(Integer jId) {
		List<JobOtherPVo> list = baseMapper.selectJobOtherP(jId);
	 	return list;
	 }
 }
