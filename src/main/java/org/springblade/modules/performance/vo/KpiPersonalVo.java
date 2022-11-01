package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 元杰
 * @Date 2022/9/15 17:09
 */
@Data
public class KpiPersonalVo {
	/**
	 * 用户id
	 */
	@TableField(value = "u_id")
	@ApiModelProperty(value="用户id")
	private Integer uId;
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
	private Date attendanceMonth;
	/**
	 * 出勤状态 1：正常出勤 2 ：借调 3：进修
	 */
	@TableField(value = "attendance_state")
	@ApiModelProperty(value = "出勤状态 1：正常出勤 2 ：借调 3：进修")
	private Integer attendanceState;
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
	 * 岗位类别
	 */
	@TableField(value = "job_gs")
	@ApiModelProperty(value = "岗位类别")
	private Integer jobGs;
	/**
	 * 固定绩效 按钮1：开  0：关
	 */
	@TableField(value = "button_fixed")
	@ApiModelProperty(value = "固定绩效 按钮1：开  0：关")
	private Boolean buttonFixed;

	/**
	 * 工作量绩效 按钮1：开 0：关
	 */
	@TableField(value = "button_workload")
	@ApiModelProperty(value = "工作量绩效 按钮1：开 0：关")
	private Boolean buttonWorkload;

	/**
	 * 其他绩效 按钮 1：开 0：关
	 */
	@TableField(value = "button_other")
	@ApiModelProperty(value = "其他绩效 按钮 1：开 0：关")
	private Boolean buttonOther;
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

}
