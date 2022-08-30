package org.springblade.modules.performance.vo;

import lombok.Data;
import org.springblade.modules.performance.entity.KpiAttendance;

/**
 * @Author 元杰
 * @Date 2022/8/30 10:59
 */
@Data
public class KpiAttendanceVo extends KpiAttendance {
	private String percentage;
}
