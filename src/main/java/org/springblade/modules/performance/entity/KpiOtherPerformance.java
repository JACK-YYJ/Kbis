package org.springblade.modules.performance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author 元杰
 * @Date 2022/9/7 18:03
 */

/**
 * 其他绩效
 */
@ApiModel(value = "org-springblade-modules-performance-entity-KpiOtherPerformance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_other_performance")
public class KpiOtherPerformance implements Serializable {
    public static final String COL_ID = "id";
    public static final String COL_KOP_ID = "kop_id";
    public static final String COL_OTHER_NIGHT_SHIFTS_PRICE = "other_night_shifts_price";
    public static final String COL_CT_NIGHT_SHIFTS_PRICE = "ct_night_shifts_price";
    public static final String COL_OVERTIME_PRICE = "overtime_price";
    public static final String COL_BEDSIDE_PRICE = "bedside_price";
	/**
	 * 计算状态0 ：插入 1：计算
	 */
	@TableField(value = "compute_status")
	@ApiModelProperty(value = "计算状态0 ：插入 1：计算")
	private Integer computeStatus;


	public static final String COL_COMPUTE_STATUS = "compute_status";
    /**
     * 出勤管理id
     */
    @TableId(value = "kpi_op_id", type = IdType.AUTO)
    @ApiModelProperty(value = "出勤管理id")
    private Integer kpiOpId;

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
     * 管理津贴（元）
     */
    @TableField(value = "manage_price")
    @ApiModelProperty(value = "管理津贴（元）")
    private BigDecimal managePrice;

    /**
     * 管理绩效（元）
     */
    @TableField(value = "manage_performance_price")
    @ApiModelProperty(value = "管理绩效（元）")
    private BigDecimal managePerformancePrice;

    /**
     * 各种奖励（元）
     */
    @TableField(value = "various_rewards_price")
    @ApiModelProperty(value = "各种奖励（元）")
    private BigDecimal variousRewardsPrice;

    /**
     * 各种考核（元）
     */
    @TableField(value = "various_assessments_price")
    @ApiModelProperty(value = "各种考核（元）")
    private BigDecimal variousAssessmentsPrice;

    /**
     * 各种补助（元）
     */
    @TableField(value = "various_subsidies_price")
    @ApiModelProperty(value = "各种补助（元）")
    private BigDecimal variousSubsidiesPrice;

    /**
     * 其他夜班费（个）
     */
    @TableField(value = "other_night_shifts_sum")
    @ApiModelProperty(value = "其他夜班费（个）")
    private BigDecimal otherNightShiftsSum;

    /**
     * CT夜班费（个）
     */
    @TableField(value = "ct_night_shifts_sum")
    @ApiModelProperty(value = "CT夜班费（个）")
    private BigDecimal ctNightShiftsSum;

    /**
     * 加班费（个）
     */
    @TableField(value = "overtime_sum")
    @ApiModelProperty(value = "加班费（个）")
    private BigDecimal overtimeSum;

    /**
     * 放射津贴（元）
     */
    @TableField(value = "radiation_price")
    @ApiModelProperty(value = "放射津贴（元）")
    private BigDecimal radiationPrice;

    /**
     * 床边（个）
     */
    @TableField(value = "bedside_sum")
    @ApiModelProperty(value = "床边（个）")
    private BigDecimal bedsideSum;

    /**
     * 合计
     */
    @TableField(value = "kpi_op_all_price")
    @ApiModelProperty(value = "合计")
    private BigDecimal kpiOpAllPrice;

    private static final long serialVersionUID = 1L;

    public static final String COL_KPI_OP_ID = "kpi_op_id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_MANAGE_PRICE = "manage_price";

    public static final String COL_MANAGE_PERFORMANCE_PRICE = "manage_performance_price";

    public static final String COL_VARIOUS_REWARDS_PRICE = "various_rewards_price";

    public static final String COL_VARIOUS_ASSESSMENTS_PRICE = "various_assessments_price";

    public static final String COL_VARIOUS_SUBSIDIES_PRICE = "various_subsidies_price";

    public static final String COL_OTHER_NIGHT_SHIFTS_SUM = "other_night_shifts_sum";

    public static final String COL_CT_NIGHT_SHIFTS_SUM = "ct_night_shifts_sum";

    public static final String COL_OVERTIME_SUM = "overtime_sum";

    public static final String COL_RADIATION_PRICE = "radiation_price";

    public static final String COL_BEDSIDE_SUM = "bedside_sum";

    public static final String COL_KPI_OP_ALL_PRICE = "kpi_op_all_price";
}
