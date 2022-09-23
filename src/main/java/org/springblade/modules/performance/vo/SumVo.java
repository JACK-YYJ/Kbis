package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 元杰
 * @Date 2022/9/15 11:21
 */
@Data
public class 	SumVo {
	@TableField(value = "attendance_month")
	@ApiModelProperty(value = "考勤月")
	private Date attendanceMonth;

	@TableField(value = "job_type")
	@ApiModelProperty(value = "岗位类型")
	private Integer jobType;

	@TableField(value = "fixed_count_score")
	@ApiModelProperty(value = "固定绩效")
	private BigDecimal fixedCountScore;

	@TableField(value = "fixed_correction_score")
	@ApiModelProperty(value = "固定绩效矫正分值")
	private BigDecimal fixedCorrectionScore;

	@TableField(value = "work_sum")
	@ApiModelProperty(value = "工作量合计")
	private BigDecimal workSum;

	@TableField(value = "work_correct")
	@ApiModelProperty(value = "工作量矫正分值")
	private BigDecimal workCorrect;

	@TableField(value = "kpi_op_all_price")
	@ApiModelProperty(value = "其他绩效 总合计")
	private BigDecimal kpiOpAllPrice;

}
