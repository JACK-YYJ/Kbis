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
 * @Date 2022/9/15 14:53
 */

@ApiModel(value = "org-springblade-modules-performance-entity-KpiAccounting")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_accounting")
public class KpiAccounting implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 出勤月
     */
    @TableField(value = "attendance_month")
    @ApiModelProperty(value = "出勤月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
    private Date attendanceMonth;

    /**
     * 科室分配绩效总额
     */
    @TableField(value = "performance_sum")
    @ApiModelProperty(value = "科室分配绩效总额")
    private BigDecimal performanceSum;

    /**
     * 其他绩效总额
     */
    @TableField(value = "onther_performance_sum")
    @ApiModelProperty(value = "其他绩效总额")
    private BigDecimal ontherPerformanceSum;

    /**
     * 可分配绩效总额
     */
    @TableField(value = "allot_sum")
    @ApiModelProperty(value = "可分配绩效总额")
    private BigDecimal allotSum;

    /**
     * 医师分配占比
     */
    @TableField(value = "phy_centum")
    @ApiModelProperty(value = "医师分配占比")
    private BigDecimal phyCentum;

    /**
     * 医技分配占比
     */
    @TableField(value = "med_centum")
    @ApiModelProperty(value = "医技分配占比")
    private BigDecimal medCentum;

    /**
     * 医师固定绩效分配系数
     */
    @TableField(value = "phy_fixed_coefficient")
    @ApiModelProperty(value = "医师固定绩效分配系数")
    private BigDecimal phyFixedCoefficient;

    /**
     * 医师工作量绩效分配系数
     */
    @TableField(value = "phy_work_coefficient")
    @ApiModelProperty(value = "医师工作量绩效分配系数")
    private BigDecimal phyWorkCoefficient;

    /**
     * 医技固定绩效分配系数
     */
    @TableField(value = "med_fixed_coefficient")
    @ApiModelProperty(value = "医技固定绩效分配系数")
    private BigDecimal medFixedCoefficient;

    /**
     * 医技工作量绩效分配系数
     */
    @TableField(value = "med_work_coefficient")
    @ApiModelProperty(value = "医技工作量绩效分配系数")
    private BigDecimal medWorkCoefficient;

    /**
     * 医师固定绩效总额
     */
    @TableField(value = "phy_fixed_sum")
    @ApiModelProperty(value = "医师固定绩效总额")
    private BigDecimal phyFixedSum;

    /**
     * 医师工作量绩效总额
     */
    @TableField(value = "phy_work_sum")
    @ApiModelProperty(value = "医师工作量绩效总额")
    private BigDecimal phyWorkSum;

    /**
     * 医技固定绩效总额
     */
    @TableField(value = "med_fixed_sum")
    @ApiModelProperty(value = "医技固定绩效总额")
    private BigDecimal medFixedSum;

    /**
     * 医技工作量绩效总额
     */
    @TableField(value = "med_work_sum")
    @ApiModelProperty(value = "医技工作量绩效总额")
    private BigDecimal medWorkSum;

    /**
     * 医师工作量绩效矫正之和A1
     */
    @TableField(value = "phy_fixed_correction_score_sum")
    @ApiModelProperty(value = "医师工作量绩效矫正之和A1")
    private BigDecimal phyFixedCorrectionScoreSum;

    /**
     * 医师工作量绩效矫正之和A2
     */
    @TableField(value = "phy_work_correct_sum")
    @ApiModelProperty(value = "医师工作量绩效矫正之和A2")
    private BigDecimal phyWorkCorrectSum;

    /**
     * 医技工作量绩效矫正之和B1
     */
    @TableField(value = "med_fixed_correction_score_sum")
    @ApiModelProperty(value = "医技固定绩效矫正之和B1")
    private BigDecimal medFixedCorrectionScoreSum;

    /**
     * 医技工作量绩效矫正之和B1
     */
    @TableField(value = "med_work_correct_sum")
    @ApiModelProperty(value = "医技工作量绩效矫正之和B2")
    private BigDecimal medWorkCorrectSum;

    /**
     * 医师固定绩每分绩效
     */
    @TableField(value = "phy_fixed_unit")
    @ApiModelProperty(value = "医师固定绩每分绩效")
    private BigDecimal phyFixedUnit;

    /**
     * 医师工作量绩效每分绩效
     */
    @TableField(value = "phy_work_unit")
    @ApiModelProperty(value = "医师工作量绩效每分绩效")
    private BigDecimal phyWorkUnit;

    /**
     * 医技固定量绩效每分绩效
     */
    @TableField(value = "med_fixed_unit")
    @ApiModelProperty(value = "医技固定量绩效每分绩效")
    private BigDecimal medFixedUnit;

    /**
     * 医技工作量绩效每分绩效
     */
    @TableField(value = "med_work_unit")
    @ApiModelProperty(value = "医技工作量绩效每分绩效")
    private BigDecimal medWorkUnit;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_PERFORMANCE_SUM = "performance_sum";

    public static final String COL_ONTHER_PERFORMANCE_SUM = "onther_performance_sum";

    public static final String COL_ALLOT_SUM = "allot_sum";

    public static final String COL_PHY_CENTUM = "phy_centum";

    public static final String COL_MED_CENTUM = "med_centum";

    public static final String COL_PHY_FIXED_COEFFICIENT = "phy_fixed_coefficient";

    public static final String COL_PHY_WORK_COEFFICIENT = "phy_work_coefficient";

    public static final String COL_MED_FIXED_COEFFICIENT = "med_fixed_coefficient";

    public static final String COL_MED_WORK_COEFFICIENT = "med_work_coefficient";

    public static final String COL_PHY_FIXED_SUM = "phy_fixed_sum";

    public static final String COL_PHY_WORK_SUM = "phy_work_sum";

    public static final String COL_MED_FIXED_SUM = "med_fixed_sum";

    public static final String COL_MED_WORK_SUM = "med_work_sum";

    public static final String COL_PHY_FIXED_CORRECTION_SCORE_SUM = "phy_fixed_correction_score_sum";

    public static final String COL_PHY_WORK_CORRECT_SUM = "phy_work_correct_sum";

    public static final String COL_MED_FIXED_CORRECTION_SCORE_SUM = "med_fixed_correction_score_sum";

    public static final String COL_MED_WORK_CORRECT_SUM = "med_work_correct_sum";

    public static final String COL_PHY_FIXED_UNIT = "phy_fixed_unit";

    public static final String COL_PHY_WORK_UNIT = "phy_work_unit";

    public static final String COL_MED_FIXED_UNIT = "med_fixed_unit";

    public static final String COL_MED_WORK_UNIT = "med_work_unit";
}
