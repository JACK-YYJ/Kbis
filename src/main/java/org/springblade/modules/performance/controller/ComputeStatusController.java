package org.springblade.modules.performance.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.ComputeCode;
import org.springblade.modules.performance.entity.ComputeStatus;
import org.springblade.modules.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/28 15:46
 */
@RestController
@RequestMapping("api/kpi/compute")
@Api(tags = "判断kbi模块数据显示的接口")
@CrossOrigin
public class ComputeStatusController {
	@Autowired
	public UserMapper userMapper;
	/**
	 * 工作类型管理分页查询
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "判断kbi模块数据显示的接口", notes = "")
	public R select(@RequestParam(value = "toMonth")String toMonth) {
		List<ComputeStatus> computeStatusList  = userMapper.selectCompute(toMonth);
		ComputeCode computeCode = new ComputeCode();
		List<ComputeCode> list = new ArrayList<>();
		ComputeCode code = new ComputeCode();
		computeStatusList.forEach(s->{
			if (s.getAcomputeStatus()==0&&s.getWcomputeStatus()==1&&s.getFcomputeStatus()==1&&s.getOpcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("出勤率！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getOpcomputeStatus()==0&&s.getWcomputeStatus()==1&&s.getFcomputeStatus()==1&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("其他绩效！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getFcomputeStatus()==0&&s.getWcomputeStatus()==1&&s.getOpcomputeStatus()==1&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("固定绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{

			if(s.getWcomputeStatus()==0&&s.getFcomputeStatus()==1&&s.getOpcomputeStatus()==1&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("工作量绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getOpcomputeStatus()==0&&s.getAcomputeStatus()==0 && s.getWcomputeStatus()==1&&s.getFcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("出勤率、其他绩效！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getFcomputeStatus()==0&&s.getOpcomputeStatus()==0&&s.getAcomputeStatus()==0&&s.getWcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("出勤率、其他绩效、固定绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getWcomputeStatus()==0&&s.getFcomputeStatus()==0&&s.getOpcomputeStatus()==0&&s.getAcomputeStatus()==0){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("出勤率、其他绩效、固定绩效分值、工作量绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getOpcomputeStatus()==0&&s.getFcomputeStatus()==0&&s.getWcomputeStatus()==1&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("其他绩效、固定绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{
			if(s.getOpcomputeStatus()==0&&s.getWcomputeStatus()==0&&s.getOpcomputeStatus()==0&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("其他绩效、固定绩效分值、工作量绩效分值！");
				list.add(computeCode);
			}
		});
		computeStatusList.forEach(s->{

			if(s.getWcomputeStatus()==0&&s.getFcomputeStatus()==0&&s.getOpcomputeStatus()==1&&s.getAcomputeStatus()==1){
				computeCode.setComputeStatus(0);
				computeCode.setCodeName("固定绩效分值、工作量绩效分值！");
				list.add(computeCode);
			}
		});

		if (list.size()==0){
			code.setComputeStatus(1);
		}else{
			code.setComputeStatus(0);
			code.setCodeName(computeCode.getCodeName());
		}
		return R.data(code);
	}
}
