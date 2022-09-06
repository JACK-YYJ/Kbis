package org.springblade.modules.performance.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 元杰
 * @Date 2022/9/5 17:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

	/**
	 * 出勤管理id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "出勤管理id")
	private Integer id;

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
}
