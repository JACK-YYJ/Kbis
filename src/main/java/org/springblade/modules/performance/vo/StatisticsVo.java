package org.springblade.modules.performance.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/15 19:27
 */
@Data
public class StatisticsVo {
	private String  name;
	private BigDecimal  personalSum;
	private String  percentage;
	private String  personSum;
	private BigDecimal avg;
}
