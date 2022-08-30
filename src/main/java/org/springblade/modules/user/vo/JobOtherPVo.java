package org.springblade.modules.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.modules.user.entity.JobOtherP;

/**
 * @Author 元杰
 * @Date 2022/8/29 11:54
 */
@Data
public class JobOtherPVo  extends JobOtherP {
	/**
	 * 绩效名称
	 */
	@TableField(value = "other_performance_name")
	@ApiModelProperty(value = "绩效名称")
	private String otherPerformanceName;
}
