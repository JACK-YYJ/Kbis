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
 * @Date 2022/9/8 16:01
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
     * 岗位证分值
     */
    @TableField(value = "job_certificate_score")
    @ApiModelProperty(value = "岗位证分值")
    private BigDecimal jobCertificateScore;

    private static final long serialVersionUID = 1L;

    public static final String COL_JC_ID = "jc_id";

    public static final String COL_JOB_CERTIFICATE_NAME = "job_certificate_name";

    public static final String COL_JOB_CERTIFICATE_SCORE = "job_certificate_score";
}