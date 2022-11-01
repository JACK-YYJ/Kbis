package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 元杰
 * @Date 2022/9/8 18:55
 */
@Data
public class FixedToMonth {
	/**
	 * 用户id
	 */
	@TableField(value = "u_id")
	@ApiModelProperty(value="用户id")
	private Integer uId;
	private Integer userCode;
	private String userName;
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
	private Date attendanceMonth;

	@ApiModelProperty(value = "职称分值")
	private BigDecimal positionScore;
	@ApiModelProperty(value="学历分值")
	private BigDecimal degreeScore;
	@ApiModelProperty(value = "工龄")
	private BigDecimal seniority;
	@ApiModelProperty(value="上岗证分值")
	private BigDecimal jcSum;
	@ApiModelProperty(value="出勤率")
	private BigDecimal percentage;
	private Byte jobGs;
	private BigDecimal jobRatio;
}
