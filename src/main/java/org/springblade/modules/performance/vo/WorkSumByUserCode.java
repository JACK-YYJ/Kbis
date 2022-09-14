package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/13 15:08
 */
@Data
public class WorkSumByUserCode {
	/**
	 * 工作类型id
	 */
	@TableField(value = "w_id")
	@ApiModelProperty(value = "工作类型id")
	private Integer wId;
	/**
	 * 工作量计分值
	 */
	@TableField(value = "w_sum")
	@ApiModelProperty(value = "工作量计分值")
	private BigDecimal wSum;


	private Byte workGS;
}
