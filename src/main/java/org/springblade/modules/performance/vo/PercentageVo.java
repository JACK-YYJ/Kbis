package org.springblade.modules.performance.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 元杰
 * @Date 2022/9/9 15:36
 */
@Data
public class PercentageVo {
	private BigDecimal Percentage;
	private Byte jobGs;
	private BigDecimal jobRatio;
}
