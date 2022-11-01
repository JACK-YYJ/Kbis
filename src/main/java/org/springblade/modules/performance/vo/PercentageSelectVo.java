package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PercentageSelectVo {
	/**
	 * 个人绩效id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "个人绩效id")
	private Integer id;


	/**
	 * 工号
	 */
	@TableField(value = "user_code")
	@ApiModelProperty(value = "工号")
	private Integer userCode;

	/**
	 * 姓名
	 */
	@TableField(value = "user_name")
	@ApiModelProperty(value = "姓名")
	private String userName;

	/**
	 * 出勤月
	 */
	@TableField(value = "attendance_month")
	@ApiModelProperty(value = "出勤月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
	private Date attendanceMonth;

	/**
	 * 出勤率
	 */
	@TableField(value = "percentage")
	@ApiModelProperty(value = "出勤率")
	private String percentage;

	/**
	 * 岗位类别
	 */
	@TableField(value = "job_type")
	@ApiModelProperty(value = "岗位类别")
	private Integer jobType;

	/**
	 * 岗位名称
	 */
	@TableField(value = "job_name")
	@ApiModelProperty(value = "岗位名称")
	private String jobName;

	/**
	 * 岗位系数
	 */
	@TableField(value = "job_ratio")
	@ApiModelProperty(value = "岗位系数")
	private BigDecimal jobRatio;

	/**
	 * 身份证
	 */
	@TableField(value = "card_id")
	@ApiModelProperty(value = "身份证")
	private String cardId;

	/**
	 * 其他绩效合计
	 */
	@TableField(value = "op_sum")
	@ApiModelProperty(value = "其他绩效合计")
	private BigDecimal opSum;

	/**
	 * 固定绩效合计
	 */
	@TableField(value = "fixed_sum")
	@ApiModelProperty(value = "固定绩效合计")
	private BigDecimal fixedSum;

	/**
	 * 工作量绩效合计
	 */
	@TableField(value = "work_sum")
	@ApiModelProperty(value = "工作量绩效合计")
	private BigDecimal workSum;

	/**
	 * 个人绩效合计
	 */
	@TableField(value = "personal_sum")
	@ApiModelProperty(value = "个人绩效合计")
	private BigDecimal personalSum;

}