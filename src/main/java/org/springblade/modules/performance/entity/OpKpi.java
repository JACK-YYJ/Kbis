package org.springblade.modules.performance.entity;

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
 * @Date 2022/9/6 17:37
 */

@ApiModel(value = "org-springblade-modules-performance-entity-OpKpi")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "op_kpi")
public class OpKpi implements Serializable {
    /**
     * 其他绩效中间表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "其他绩效中间表")
    private Integer id;

    /**
     * 记录中间其他绩效 id
     */
    @TableField(value = "kop_id")
    @ApiModelProperty(value = "记录中间其他绩效 id")
    private String kopId;

    /**
     * 其他绩效字典表id
     */
    @TableField(value = "op_id")
    @ApiModelProperty(value = "其他绩效字典表id")
    private Integer opId;

    /**
     * 其他绩效核算数
     */
    @TableField(value = "op_price")
    @ApiModelProperty(value = "其他绩效核算数")
    private BigDecimal opPrice;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_KOP_ID = "kop_id";

    public static final String COL_OP_ID = "op_id";

    public static final String COL_OP_PRICE = "op_price";
}