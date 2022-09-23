package org.springblade.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.entity.SysUser;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.Degree;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.DegreeService;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/25 16:52
 */
@RestController
@RequestMapping("api/set/degree")
@Api(tags = "学历字典表（字典表）")
@CrossOrigin
public class DegreeController extends BladeController {
	@Autowired
	public DegreeService degreeService;
	@Autowired
	public UserService userService;
	/**
	 * 学历字典表分页查询
	 */
	@GetMapping("/selectDegreePage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "")
	public R<IPage<Degree>> selectDegreePage(Query page, String idOrName) {
		IPage<Degree> pages = degreeService.selectDegreePage(Condition.getPage(page),idOrName);
		return R.data(pages);
	}

	/**
	 * 学历字典表 add
	 * @param param
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiOperationSupport(order = 2)
	public R add(@RequestBody Degree param) {
		Degree em = degreeService.getOne(new QueryWrapper<Degree>().eq(Degree.COL_DEGREE_NAME,param.getDegreeName()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		degreeService.save(param);
		return R.success("添加成功");
	}

	/**
	 * 学历字典表 update
	 * @param param
	 * @return
	 */

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody Degree param) {
		degreeService.updateById(param);
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
		int count = userService.count();
		if(count==0){
			degreeService.removeByIds(param);
		}else {
			for (Integer s : param) {
				List<User> list = userService.lambdaQuery().eq(User::getDId, s).list();
				if (!(list.size() == 0)) {
					return R.fail("该学历下存在用户，不可删除");
				}
			}
			degreeService.removeByIds(param);
		}
		return R.success("删除成功");
	}
}
