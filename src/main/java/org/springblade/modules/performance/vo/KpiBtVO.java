package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Author 元杰
 * @Date 2022/9/6 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KpiBtVO {
	/**
	 * 其他绩效字典表id
	 */
	@TableField(value = "op_id")
	@ApiModelProperty(value = "其他绩效字典表id")
	private Integer opId;

	/**
	 * 绩效名称
	 */
	@TableField(value = "other_performance_name")
	@ApiModelProperty(value = "绩效名称")
	private String otherPerformanceName;



}
