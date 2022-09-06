package org.springblade.modules.util;

import com.alibaba.excel.EasyExcel;
import org.springblade.modules.user.entity.OtherPerformance;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/3 9:49
 */
public class EasyExcelUtil {
	public static void hand(List<OtherPerformance> otherPerformanceList){
		String fileName = "D:\\tmp" + "其他绩效"+ ".xlsx";

		//数据列表
		List<List<String>> dataList = new ArrayList<>();
		//表头
		List<List<String>> header = new ArrayList<>();

		List<String> cellContain1 = new ArrayList<>();
		cellContain1.add("技师信息");
		cellContain1.add("用户工号");
		header.add(cellContain1);

		List<String> cellContain2 = new ArrayList<>();
		cellContain2.add("技师信息");
		cellContain2.add("姓名");
		header.add(cellContain2);
		List<String> cellContain3 = new ArrayList<>();
		cellContain3.add("技师信息");
		cellContain3.add("出勤月");
		header.add(cellContain3);

		for (int i = 0; i < otherPerformanceList.size(); i++) {
			List<String> list = new ArrayList<>();
			list.add("其他绩效字典表");
			list.add(otherPerformanceList.get(i).getOtherPerformanceName());
			header.add(list);
		}
		EasyExcel.write(fileName)
			// 这里放入动态头
			.head(header).sheet("TEST")
			.doWrite(dataList);
	}
}

