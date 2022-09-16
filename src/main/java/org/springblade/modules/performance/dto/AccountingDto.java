package org.springblade.modules.performance.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 元杰
 * @Date 2022/9/15 11:33
 */
@Data
public class AccountingDto {
	/**
	 * 出勤月
	 */

	@ApiModelProperty(value = "出勤月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
	private String toMonth;

	/**
	 * 科室分配绩效总额
	 */
	@TableField(value = "performance_sum")
	@ApiModelProperty(value = "科室分配绩效总额")
	private BigDecimal performanceSum;

	/**
	 * 医师分配占比
	 */
	@TableField(value = "phy_centum")
	@ApiModelProperty(value = "医师分配占比")
	private BigDecimal phyCentum;

	/**
	 * 医师固定绩效分配系数
	 */
	@TableField(value = "phy_fixed_coefficient")
	@ApiModelProperty(value = "医师固定绩效分配系数")
	private BigDecimal phyFixedCoefficient;

	/**
	 * 医技固定绩效分配系数
	 */
	@TableField(value = "med_fixed_coefficient")
	@ApiModelProperty(value = "医技固定绩效分配系数")
	private BigDecimal medFixedCoefficient;

}
