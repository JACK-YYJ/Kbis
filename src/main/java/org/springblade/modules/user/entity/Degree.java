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
 * @Date 2022/8/25 11:25
 */

/**
 * 学历字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-Degree")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_degree")
public class Degree implements Serializable {
    /**
     * 学历id
     */
    @TableId(value = "d_id", type = IdType.AUTO)
    @ApiModelProperty(value = "学历id")
    private Integer dId;

    /**
     * 学历名称
     */
    @TableField(value = "degree_name")
    @ApiModelProperty(value = "学历名称")
    private String degreeName;

    /**
     * 学历分值
     */
    @TableField(value = "degree_score")
    @ApiModelProperty(value = "学历分值")
    private Double degreeScore;

    private static final long serialVersionUID = 1L;

    public static final String COL_D_ID = "d_id";

    public static final String COL_DEGREE_NAME = "degree_name";

    public static final String COL_DEGREE_SCORE = "degree_score";
}