package org.springblade.modules.performance.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 元杰
 * @Date 2022/9/28 15:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputeCode {
	@ApiModelProperty(value = "0: 还没有计算 1：正常页面展示")
	private Integer computeStatus;
	@ApiModelProperty(value = "还没有计算的模块")
	private String codeName;

}
