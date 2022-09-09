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
    * 个人绩效合计
    */
@ApiModel(value="org-springblade-modules-performance-entity-KpiPersonal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_personal")
public class KpiPersonal implements Serializable {
    /**
     * 固定绩效id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="固定绩效id")
    private Integer id;

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
     * 出勤率
     */
    @TableField(value = "percentage")
    @ApiModelProperty(value="出勤率")
    private String percentage;

    /**
     * 岗位类别
     */
    @TableField(value = "job_type")
    @ApiModelProperty(value="岗位类别")
    private Byte jobType;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    @ApiModelProperty(value="岗位名称")
    private String jobName;

    /**
     * 岗位系数
     */
    @TableField(value = "job_ratio")
    @ApiModelProperty(value="岗位系数")
    private Double jobRatio;

    /**
     * 身份证
     */
    @TableField(value = "card_id")
    @ApiModelProperty(value="身份证")
    private String cardId;

    /**
     * 其他绩效合计
     */
    @TableField(value = "op_sum")
    @ApiModelProperty(value="其他绩效合计")
    private BigDecimal opSum;

    /**
     * 固定绩效合计
     */
    @TableField(value = "fixed_sum")
    @ApiModelProperty(value="固定绩效合计")
    private BigDecimal fixedSum;

    /**
     * 工作量绩效合计
     */
    @TableField(value = "work_sum")
    @ApiModelProperty(value="工作量绩效合计")
    private BigDecimal workSum;

    /**
     * 个人绩效合计
     */
    @TableField(value = "personal_sum")
    @ApiModelProperty(value="个人绩效合计")
    private BigDecimal personalSum;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_PERCENTAGE = "percentage";

    public static final String COL_JOB_TYPE = "job_type";

    public static final String COL_JOB_NAME = "job_name";

    public static final String COL_JOB_RATIO = "job_ratio";

    public static final String COL_CARD_ID = "card_id";

    public static final String COL_OP_SUM = "op_sum";

    public static final String COL_FIXED_SUM = "fixed_sum";

    public static final String COL_WORK_SUM = "work_sum";

    public static final String COL_PERSONAL_SUM = "personal_sum";
}
