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
 * @Date 2022/8/26 18:21
 */

/**
 * 职称字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-Position")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_position")
public class Position implements Serializable {
    /**
     * 职称id
     */
    @TableId(value = "p_id", type = IdType.AUTO)
    @ApiModelProperty(value = "职称id")
    private Integer pId;

    /**
     * 职称名称
     */
    @TableField(value = "position_name")
    @ApiModelProperty(value = "职称名称")
    private String positionName;

    /**
     * 职称分值
     */
    @TableField(value = "position_score")
    @ApiModelProperty(value = "职称分值")
    private Integer positionScore;

    private static final long serialVersionUID = 1L;

    public static final String COL_P_ID = "p_id";

    public static final String COL_POSITION_NAME = "position_name";

    public static final String COL_POSITION_SCORE = "position_score";
}
