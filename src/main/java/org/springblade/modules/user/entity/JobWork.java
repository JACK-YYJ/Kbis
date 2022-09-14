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
 * @Date 2022/9/13 15:13
 */

/**
 * 岗位工作类型关系表
 */
@ApiModel(value = "org-springblade-modules-user-entity-JobWork")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "job_work")
public class JobWork implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 岗位id
     */
    @TableField(value = "j_id")
    @ApiModelProperty(value = "岗位id")
    private Integer jId;

    /**
     * 工作类型id
     */
    @TableField(value = "w_id")
    @ApiModelProperty(value = "工作类型id")
    private Integer wId;

    /**
     * 工作量计分值
     */
    @TableField(value = "w_sum")
    @ApiModelProperty(value = "工作量计分值")
    private BigDecimal wSum;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_J_ID = "j_id";

    public static final String COL_W_ID = "w_id";

    public static final String COL_W_SUM = "w_sum";
}