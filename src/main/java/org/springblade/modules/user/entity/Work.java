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
 * @Date 2022/8/26 19:15
 */

/**
 * 工作类型字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-Work")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_work")
public class Work implements Serializable {
    public static final String COL_W_SUM = "w_sum";
    /**
     * 工作类型id
     */
    @TableId(value = "w_id", type = IdType.AUTO)
    @ApiModelProperty(value = "工作类型id")
    private Integer wId;

    /**
     * 工作类型名称
     */
    @TableField(value = "work_name")
    @ApiModelProperty(value = "工作类型名称")
    private String workName;

    private static final long serialVersionUID = 1L;

    public static final String COL_W_ID = "w_id";

    public static final String COL_WORK_NAME = "work_name";
}