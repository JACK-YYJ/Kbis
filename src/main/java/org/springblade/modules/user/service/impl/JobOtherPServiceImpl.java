package org.springblade.modules.user.service.impl;

import org.springblade.modules.user.entity.JobWork;
import org.springblade.modules.user.entity.Work;
import org.springblade.modules.user.vo.JobOtherPVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
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

	 @Override
	 public void add(Integer jId) {
		 List<JobOtherP> list = this.lambdaQuery().list();
		 ArrayList<JobOtherP> jobOtherPList = new ArrayList<>();
		 for (JobOtherP j : list) {
			 JobOtherP jobOtherP = new JobOtherP();
			 jobOtherP.setOpId(j.getOpId());
			 jobOtherP.setJId(jId);
			 jobOtherPList.add(jobOtherP);
		 }
		 this.saveBatch(jobOtherPList);
	 }
 }
