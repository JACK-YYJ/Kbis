package org.springblade.modules.performance.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author 元杰
 * @Date 2022/8/30 13:11
 */

@ApiModel(value = "org-springblade-modules-performance-entity-KpiAttendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kpi_attendance")
public class KpiAttendance implements Serializable {
    /**
     * 出勤管理id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "出勤管理id")
    private Integer id;

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
     * 出勤月
     */
    @TableField(value = "attendance_month")
    @ApiModelProperty(value = "出勤月")
	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM")
    private Date attendanceMonth;

    /**
     * 出勤状态 1：正常出勤 2 ：借调 3：进修
     */
    @TableField(value = "attendance_state")
    @ApiModelProperty(value = "出勤状态 1：正常出勤 2 ：借调 3：进修")
    private Integer attendanceState;

    /**
     * 出勤天数
     */
    @TableField(value = "attendance_day")
    @ApiModelProperty(value = "出勤天数")
    private Integer attendanceDay;

    /**
     * 全勤天数
     */
    @TableField(value = "month_day")
    @ApiModelProperty(value = "全勤天数")
    private Integer monthDay;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_ATTENDANCE_MONTH = "attendance_month";

    public static final String COL_ATTENDANCE_STATE = "attendance_state";

    public static final String COL_ATTENDANCE_DAY = "attendance_day";

    public static final String COL_MONTH_DAY = "month_day";
}
