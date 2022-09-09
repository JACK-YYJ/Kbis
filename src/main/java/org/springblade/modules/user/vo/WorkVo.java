package org.springblade.modules.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.modules.user.entity.Work;

/**
 * @Author 元杰
 * @Date 2022/9/9 17:45
 */
@Data
public class WorkVo extends Work {
	/**
	 * 工作量计分值
	 */
	@TableField(value = "w_sum")
	@ApiModelProperty(value = "工作量计分值")
	private Double wSum;
}
