package org.springblade.modules.performance.entity;

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
 * @Date 2022/9/6 10:03
 */

@ApiModel(value = "org-springblade-modules-performance-entity-KpiOtherPerformance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_other_performance")
public class KpiOtherPerformance implements Serializable {
    public static final String COL_ID = "id";
    /**
     * 出勤管理id
     */
    @TableId(value = "kpi_op_id", type = IdType.AUTO)
    @ApiModelProperty(value = "出勤管理id")
    private Integer kpiOpId;

    /**
     * 工号
     */
    @TableField(value = "user_code")
    @ApiModelProperty(value = "工号")
    private String userCode;

    /**
     * 姓名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 出勤月
     */
    @TableField(value = "attendance_month")
    @ApiModelProperty(value = "出勤月")
    private String attendanceMonth;

    /**
     * 记录中间其他绩效 id
     */
    @TableField(value = "kop_id")
    @ApiModelProperty(value = "记录中间其他绩效 id")
    private String kopId;

    private static final long serialVersionUID = 1L;

    public static final String COL_KPI_OP_ID = "kpi_op_id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_KOP_ID = "kop_id";
}