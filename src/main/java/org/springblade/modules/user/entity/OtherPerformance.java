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
 * @Date 2022/8/29 10:32
 */

/**
 * 其他绩效字典表
 */
@ApiModel(value = "org-springblade-modules-user-entity-OtherPerformance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_other_performance")
public class OtherPerformance implements Serializable {
    /**
     * 其他绩效
     */
    @TableId(value = "op_id", type = IdType.INPUT)
    @ApiModelProperty(value = "其他绩效")
    private Integer opId;

    /**
     * 绩效名称
     */
    @TableField(value = "other_performance_name")
    @ApiModelProperty(value = "绩效名称")
    private String otherPerformanceName;

    /**
     * 1 ：总金额 2 ：单价
     */
    @TableField(value = "op_type")
    @ApiModelProperty(value = "1 ：总金额 2 ：单价")
    private Integer opType;

    /**
     * 单价（op_sum）
     */
    @TableField(value = "op_sum")
    @ApiModelProperty(value = "单价（op_sum）")
    private Integer opSum;
	/**
	 * 绩效名称
	 */
	@TableField(value = "op_bt_name")
	@ApiModelProperty(value = "绩效名称")
	private String opBtName;

    private static final long serialVersionUID = 1L;

    public static final String COL_OP_ID = "op_id";

    public static final String COL_OTHER_PERFORMANCE_NAME = "other_performance_name";

    public static final String COL_OP_TYPE = "op_type";

    public static final String COL_OP_SUM = "op_sum";
}
