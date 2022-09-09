package org.springblade.modules.performance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

/**
    * 工作量绩效分值
    */
@ApiModel(value="org-springblade-modules-performance-entity-KpiWorkload")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_workload")
public class KpiWorkload implements Serializable {
    /**
     * 工作量核算分值id
     */
    @TableId(value = "kpi_work_id", type = IdType.AUTO)
    @ApiModelProperty(value="工作量核算分值id")
    private Integer kpiWorkId;

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
    private Date attendanceMonth;

    /**
     * 平片（个）
     */
    @TableField(value = "plain_film_sum")
    @ApiModelProperty(value="平片（个）")
    private Integer plainFilmSum;

    /**
     * 工作量绩效合计
     */
    @TableField(value = "work_sum")
    @ApiModelProperty(value="工作量绩效合计")
    private BigDecimal workSum;

    /**
     * 肠胃/特检
     */
    @TableField(value = "bowel_sum")
    @ApiModelProperty(value="肠胃/特检")
    private Integer bowelSum;

    /**
     * 乳腺
     */
    @TableField(value = "breast_sum")
    @ApiModelProperty(value="乳腺")
    private Integer breastSum;

    /**
     * CT平扫
     */
    @TableField(value = "ct_flat_sweep_sum")
    @ApiModelProperty(value="CT平扫")
    private Integer ctFlatSweepSum;

    /**
     * CT增强
     */
    @TableField(value = "ct_intensifier_sum")
    @ApiModelProperty(value="CT增强")
    private Integer ctIntensifierSum;

    /**
     * CT三维/定位
     */
    @TableField(value = "ct_positioning_sum")
    @ApiModelProperty(value="CT三维/定位")
    private Integer ctPositioningSum;

    /**
     * CT血管
     */
    @TableField(value = "ct_hemal_sum")
    @ApiModelProperty(value="CT血管")
    private Integer ctHemalSum;

    /**
     * MR增强
     */
    @TableField(value = "mr_intensifier_sum")
    @ApiModelProperty(value="MR增强")
    private Integer mrIntensifierSum;

    /**
     * 功能MR成像
     */
    @TableField(value = "mr_image_sum")
    @ApiModelProperty(value="功能MR成像")
    private Integer mrImageSum;

    /**
     * MR乳腺/心脏
     */
    @TableField(value = "mr_breast_sum")
    @ApiModelProperty(value="MR乳腺/心脏")
    private Integer mrBreastSum;

    private static final long serialVersionUID = 1L;

    public static final String COL_KPI_WORK_ID = "kpi_work_id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_PLAIN_FILM_SUM = "plain_film_sum";

    public static final String COL_WORK_SUM = "work_sum";

    public static final String COL_BOWEL_SUM = "bowel_sum";

    public static final String COL_BREAST_SUM = "breast_sum";

    public static final String COL_CT_FLAT_SWEEP_SUM = "ct_flat_sweep_sum";

    public static final String COL_CT_INTENSIFIER_SUM = "ct_intensifier_sum";

    public static final String COL_CT_POSITIONING_SUM = "ct_positioning_sum";

    public static final String COL_CT_HEMAL_SUM = "ct_hemal_sum";

    public static final String COL_MR_INTENSIFIER_SUM = "mr_intensifier_sum";

    public static final String COL_MR_IMAGE_SUM = "mr_image_sum";

    public static final String COL_MR_BREAST_SUM = "mr_breast_sum";
}