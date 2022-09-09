package org.springblade.modules.performance.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/8 17:28
 */
@Data
public class SetFactorDto {
	@TableField(value = "job_certificate_factor")
	@ApiModelProperty(value = "岗位证系数")
	private BigDecimal jobCertificateFactor;

	/**
	 * 工龄系数
	 */
	@TableField(value = "age_factor")
	@ApiModelProperty(value = "工龄系数")
	private BigDecimal ageFactor;
}
