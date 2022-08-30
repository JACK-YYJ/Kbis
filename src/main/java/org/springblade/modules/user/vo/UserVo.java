package org.springblade.modules.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 元杰
 * @Date 2022/8/23 17:33
 */
@Data
public class UserVo {
    /**
     * 用户id
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
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
     * 身份证
     */
    @TableField(value = "card_id")
    @ApiModelProperty(value = "身份证")
    private String cardId;

    /**
     * 岗位名称
     */
    @TableField(value = "job_name")
    @ApiModelProperty(value="岗位名称")
    private String jobName;

    /**
     * 职称名称
     */
    @TableField(value = "position_name")
    @ApiModelProperty(value="职称名称")
    private String positionName;

    /**
     * 学历名称
     */
    @TableField(value = "degree_name")
    @ApiModelProperty(value="学历名称")
    private String degreeName;

//    /**
//     * 工作类型名称
//     */
//    @TableField(value = "work_name")
//    @ApiModelProperty(value="工作类型名称")
//    private String workName;

    /**
     * 工龄
     */
    @ApiModelProperty(value = "工龄")
    private String seniority;

    /**
     * 岗位证名称
     */
    @TableField(value = "job_certificate_name")
    @ApiModelProperty(value="岗位证名称")
    private String jobCertificateName;

}
