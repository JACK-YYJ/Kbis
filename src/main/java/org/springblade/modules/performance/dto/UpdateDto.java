package org.springblade.modules.performance.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 元杰
 * @Date 2022/8/30 10:05
 */
@Data
public class UpdateDto {
	@ApiModelProperty(value = "全勤天数")
	private Integer ToDaySum;
	@ApiModelProperty(value = "输入的月份 YYYY—mm-dd（修改的）")
	private String toMonth;
}
