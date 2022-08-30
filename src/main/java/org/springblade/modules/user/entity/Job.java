package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
 * @Author 元杰
 * @Date 2022/8/29 11:22
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
    @TableId(value = "j_id", type = IdType.AUTO)
    @ApiModelProperty(value = "岗位id")
    private Integer jId;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    @ApiModelProperty(value = "岗位名称")
    private String jobName;

    /**
     * 岗位类别
     */
    @TableField(value = "job_type")
    @ApiModelProperty(value = "岗位类别")
    private String jobType;

    /**
     * 岗位系数
     */
    @TableField(value = "job_ratio")
    @ApiModelProperty(value = "岗位系数")
    private Double jobRatio;

    /**
     * 矫正固定绩效分值（公式id）
     */
    @TableField(value = "formula_fixed")
    @ApiModelProperty(value = "矫正固定绩效分值（公式id）")
    private Integer formulaFixed;

    /**
     * 矫正固定绩效分值
     */
    @TableField(value = "fixed_than")
    @ApiModelProperty(value = "矫正固定绩效分值")
    private String fixedThan;

    /**
     * 矫正工作量分值（公式id）
     */
    @TableField(value = "formula_workload")
    @ApiModelProperty(value = "矫正工作量分值（公式id）")
    private Integer formulaWorkload;

    /**
     * 矫正工作量分值
     */
    @TableField(value = "workload_than")
    @ApiModelProperty(value = "矫正工作量分值")
    private String workloadThan;

    /**
     * 固定绩效 按钮1：开  0：关
     */
    @TableField(value = "button_fixed")
    @ApiModelProperty(value = "固定绩效 按钮1：开  0：关")
    private Integer buttonFixed;

    /**
     * 固定绩效详情
     */
    @TableField(value = "fixed_text")
    @ApiModelProperty(value = "固定绩效详情")
    private String fixedText;

    /**
     * 工作量绩效 按钮1：开 0：关
     */
    @TableField(value = "button_workload")
    @ApiModelProperty(value = "工作量绩效 按钮1：开 0：关")
    private Integer buttonWorkload;

    /**
     * 工作量绩效详情
     */
    @TableField(value = "workload_text")
    @ApiModelProperty(value = "工作量绩效详情")
    private String workloadText;

    /**
     * 其他绩效 按钮 1：开 0：关
     */
    @TableField(value = "button_other")
    @ApiModelProperty(value = "其他绩效 按钮 1：开 0：关")
    private Integer buttonOther;

    private static final long serialVersionUID = 1L;

    public static final String COL_J_ID = "j_id";

    public static final String COL_JOB_NAME = "job_name";

    public static final String COL_JOB_TYPE = "job_type";

    public static final String COL_JOB_RATIO = "job_ratio";

    public static final String COL_FORMULA_FIXED = "formula_fixed";

    public static final String COL_FIXED_THAN = "fixed_than";

    public static final String COL_FORMULA_WORKLOAD = "formula_workload";

    public static final String COL_WORKLOAD_THAN = "workload_than";

    public static final String COL_BUTTON_FIXED = "button_fixed";

    public static final String COL_FIXED_TEXT = "fixed_text";

    public static final String COL_BUTTON_WORKLOAD = "button_workload";

    public static final String COL_WORKLOAD_TEXT = "workload_text";

    public static final String COL_BUTTON_OTHER = "button_other";
}
