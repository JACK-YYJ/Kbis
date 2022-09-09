package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
 * @Author 元杰
 * @Date 2022/9/8 17:38
 */

/**
 * 岗位证字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-JobCertificate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_job_certificate")
public class JobCertificate implements Serializable {
    public static final String COL_JOB_CERTIFICATE_SCORE = "job_certificate_score";
    /**
     * 岗位证id
     */
    @TableId(value = "jc_id", type = IdType.AUTO)
    @ApiModelProperty(value = "岗位证id")
    private Integer jcId;

    /**
     * 岗位证名称
     */
    @TableField(value = "job_certificate_name")
    @ApiModelProperty(value = "岗位证名称")
    private String jobCertificateName;

    /**
     * 岗位证系数
     */
    @TableField(value = "job_certificate_factor")
    @ApiModelProperty(value = "岗位证系数")
    private BigDecimal jobCertificateFactor;

    /**
     * 工龄系数
     */
    @TableField(value = "age_factor")
    @ApiModelProperty(value = "工龄系数")
    private BigDecimal ageFactor;

    private static final long serialVersionUID = 1L;

    public static final String COL_JC_ID = "jc_id";

    public static final String COL_JOB_CERTIFICATE_NAME = "job_certificate_name";

    public static final String COL_JOB_CERTIFICATE_FACTOR = "job_certificate_factor";

    public static final String COL_AGE_FACTOR = "age_factor";
}