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
 * @Date 2022/9/7 14:57
 */

/**
    * 固定绩效分值
    */
@ApiModel(value="org-springblade-modules-performance-entity-KpiFixed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_fixed")
public class KpiFixed implements Serializable {
    /**
     * 固定绩效id
     */
    @TableId(value = "kpi_fixed_id", type = IdType.AUTO)
    @ApiModelProperty(value="固定绩效id")
    private Integer kpiFixedId;

    /**
     * 工号
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value="工号")
    private Integer userCode;

    /**
     * 姓名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value="姓名")
    private String userName;

    /**
     * 出勤月
     */
    @TableField(value = "attendance_month")
    @ApiModelProperty(value="出勤月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
    private Date attendanceMonth;

    /**
     * 职称分值
     */
    @TableField(value = "position_score")
    @ApiModelProperty(value="职称分值")
    private BigDecimal positionScore;

    /**
     * 学历分值
     */
    @TableField(value = "degree_score")
    @ApiModelProperty(value="学历分值")
    private BigDecimal degreeScore;

    /**
     * 工龄
     */
    @TableField(value = "seniority")
    @ApiModelProperty(value="工龄")
    private BigDecimal seniority;

    /**
     * 工龄分值
     */
    @TableField(value = "seniority_score")
    @ApiModelProperty(value="工龄分值")
    private BigDecimal seniorityScore;

    /**
     * 上岗证（个）
     */
    @TableField(value = "jc_sum")
    @ApiModelProperty(value="上岗证分值")
    private BigDecimal jcSum;

    /**
     * 三基考试
     */
    @TableField(value = "three_exam")
    @ApiModelProperty(value="三基考试")
    private BigDecimal threeExam;

    /**
     * 合计分值
     */
    @TableField(value = "fixed_count_score")
    @ApiModelProperty(value="合计分值")
    private BigDecimal fixedCountScore;

    /**
     * 矫正固定分值
     */
    @TableField(value = "fixed_correction_score")
    @ApiModelProperty(value="矫正固定分值")
    private BigDecimal fixedCorrectionScore;


    private static final long serialVersionUID = 1L;

    public static final String COL_KPI_FIXED_ID = "kpi_fixed_id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_POSITION_SCORE = "position_score";

    public static final String COL_DEGREE_SCORE = "degree_score";

    public static final String COL_SENIORITY = "seniority";

    public static final String COL_SENIORITY_SCORE = "seniority_score";

    public static final String COL_JC_SUM = "jc_sum";

    public static final String COL_THREE_EXAM = "three_exam";

    public static final String COL_FIXED_COUNT_SCORE = "fixed_count_score";

    public static final String COL_FIXED_CORRECTION_SCORE = "fixed_correction_score";

}
