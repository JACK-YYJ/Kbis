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
 * @Date 2022/8/29 11:30
 */

/**
    * 岗位其他绩效关系表
    */
@ApiModel(value="org-springblade-modules-user-entity-JobOtherP")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "job_other_p")
public class JobOtherP implements Serializable {
    /**
     * 岗位其他绩效中间id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="岗位其他绩效中间id，非必传（自增）")
    private Integer id;

    /**
     * 岗位id
     */
    @TableField(value = "j_id")
    @ApiModelProperty(value="岗位id")
    private Integer jId;

    /**
     * 其他绩效id
     */
    @TableField(value = "op_id")
    @ApiModelProperty(value="其他绩效id")
    private Integer opId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_J_ID = "j_id";

    public static final String COL_OP_ID = "op_id";
}
