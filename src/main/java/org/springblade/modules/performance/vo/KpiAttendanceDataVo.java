package org.springblade.modules.performance.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 元杰
 * @Date 2022/9/6 9:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KpiAttendanceDataVo {
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
	@ApiModelProperty(value="工号")
	private String userCode;

	/**
	 * 姓名
	 */
	@TableField(value = "user_name")
	@ApiModelProperty(value="姓名")
	private String userName;

	/**
	 * 出勤月
	 */
	@TableField(value = "attendance_month")
	@ApiModelProperty(value="出勤月")
	private String attendanceMonth;


	private List<HashMap<String, String>> kpiOpList;
}
