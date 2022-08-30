package org.springblade.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.user.entity.Work;
import org.springblade.modules.user.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/26 13:14
 */
@RestController
@RequestMapping("api/set/work")
@Api(tags = "工作类型管理")
public class WorkController {
	@Autowired
	private WorkService workService;

	/**
	 * 工作类型管理分页查询
	 */
	@GetMapping("/selectWorkPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入UserDto")
	public R<IPage<Work>> selectWorkPage(Query page, String idOrName) {
		IPage<Work> pages = workService.selectWorkPage(Condition.getPage(page),idOrName);
		return R.data(pages);
	}

	/**
	 * 工作类型管理 add
	 * @param param
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiOperationSupport(order = 2)
	public R add(@RequestBody Work param) {
		Work em = workService.getOne(new QueryWrapper<Work>().eq(Work.COL_WORK_NAME,param.getWorkName()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		workService.save(param);
		return R.success("添加成功");
	}

	/**
	 * 工作类型管理 update
	 * @param param
	 * @return
	 */

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody Work param) {
		workService.updateById(param);
		return R.success("操作成功");
	}

	/**
	 * 批量删除
	 *
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除")
	@ApiOperationSupport(order = 4)
	public R delete(@RequestBody List<Integer> param) {
		return R.data(workService.removeByIds(param));
	}
}
