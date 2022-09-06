package org.springblade.modules.user.dto;

import lombok.Data;
import org.springblade.modules.user.entity.Job;
import org.springblade.modules.user.entity.JobOtherP;
import org.springblade.modules.user.entity.JobWork;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/1 9:11
 */
@Data
public class UpdateJobDto extends Job {
	private List<JobWork> jobWorkList;
	private List<JobOtherP> jobOtherPList;
}
