package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/20 17:22
 */
@Data
public class kpiWorkloadVo {
	private Integer jobType;

	/**
	 * 工作量绩效合计
	 */
	@TableField(value = "work_sum")
	@ApiModelProperty(value = "工作量绩效合计")
	private BigDecimal workSum;
}
