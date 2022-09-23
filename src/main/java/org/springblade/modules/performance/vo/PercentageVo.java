package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/9 15:36
 */
@Data
public class PercentageVo {
	private BigDecimal Percentage;
	private Integer jobGs;
	private Integer workGs;
	private Integer jobType;
	/**
	 * 岗位系数
	 */
	@TableField(value = "job_ratio")
	@ApiModelProperty(value = "岗位系数")
	private BigDecimal jobRatio;
}
