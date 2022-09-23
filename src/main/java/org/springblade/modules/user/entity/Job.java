package org.springblade.modules.user.entity;

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
 * @Date 2022/9/19 16:08
 */

/**
 * 岗位字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-Job")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_job")
public class Job implements Serializable {
    /**
     * 岗位id
     */
    @TableId(value = "j_id", type = IdType.INPUT)
    @ApiModelProperty(value = "岗位id")
    private Integer jId;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    @ApiModelProperty(value = "岗位名称")
    private String jobName;

    /**
     * 矫正固定绩效分值 计算公式
     */
    @TableField(value = "job_gs")
    @ApiModelProperty(value = "矫正固定绩效分值 计算公式")
    private Integer jobGs;

    /**
     * 矫正工作量分值 计算公式
     */
    @TableField(value = "work_gs")
    @ApiModelProperty(value = "矫正工作量分值 计算公式")
    private Integer workGs;

    /**
     * 岗位类别（0：医师   1：医技）
     */
    @TableField(value = "job_type")
    @ApiModelProperty(value = "岗位类别（0：医师   1：医技）")
    private Byte jobType;

    /**
     * 岗位系数
     */
    @TableField(value = "job_ratio")
    @ApiModelProperty(value = "岗位系数")
    private BigDecimal jobRatio;

    /**
     * 矫正固定绩效分值（公式id）
     */
    @TableField(value = "formula_fixed")
    @ApiModelProperty(value = "矫正固定绩效分值（公式id）")
    private Integer formulaFixed;

    /**
     * 矫正工作量分值（公式id）
     */
    @TableField(value = "formula_workload")
    @ApiModelProperty(value = "矫正工作量分值（公式id）")
    private Integer formulaWorkload;

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
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_J_ID = "j_id";

    public static final String COL_JOB_NAME = "job_name";

    public static final String COL_JOB_GS = "job_gs";

    public static final String COL_WORK_GS = "work_gs";

    public static final String COL_JOB_TYPE = "job_type";

    public static final String COL_JOB_RATIO = "job_ratio";

    public static final String COL_FORMULA_FIXED = "formula_fixed";

    public static final String COL_FORMULA_WORKLOAD = "formula_workload";

    public static final String COL_BUTTON_FIXED = "button_fixed";

    public static final String COL_BUTTON_WORKLOAD = "button_workload";

    public static final String COL_BUTTON_OTHER = "button_other";

    public static final String COL_CREATE_TIME = "create_time";
}
