package org.springblade.modules.user.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.service.KpiAttendanceService;
import org.springblade.modules.performance.service.KpiPersonalService;
import org.springblade.modules.system.dto.SysUserPwdDto;
import org.springblade.modules.system.entity.SysUser;
import org.springblade.modules.system.service.SysUserService;
import org.springblade.modules.user.dto.UserDto;
import org.springblade.modules.user.entity.User;
import org.springblade.modules.user.service.UserService;
import org.springblade.modules.user.tool.utils.ShiroUtils;
import org.springblade.modules.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/8/23 17:06
 */
@RestController
@RequestMapping("api/set/user")
@Api(tags = "用户管理（字典表）")
@CrossOrigin
public class UserController {
	@Autowired
	public UserService userService;
	@Autowired
	public SysUserService sysUserService;

	@Autowired
	private KpiPersonalService kpiPersonalService;
	/**
	 * 用户分页查询
	 */
	@GetMapping("/selectUserPage")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "条件分页查询", notes = "传入UserDto")
	public R<IPage<UserVo>> selectUserPage(Query page, UserDto param) {
		IPage<UserVo> pages = userService.selectUserPage(Condition.getPage(page), param);
		return R.data(pages);
	}
	/**
	 * 用户分页查询
	 */
	@GetMapping("/selectUserOfCreateTime")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "添加数据回显（按照创建时间倒序）", notes = "")
	public R selectUserOfCreateTime() {
		List<User> pages = userService.lambdaQuery().orderByDesc(User::getCreateTime).list();
		return R.data(pages);
	}


	@PostMapping("/add")
	@ApiOperation(value = "添加")
	@ApiOperationSupport(order = 2)
	public R add(@RequestBody User param) {
		User em = userService.getOne(new QueryWrapper<User>().eq("user_code", param.getUserCode()));
		if (em != null) {
			return R.fail("请勿重复添加");
		}
		//创建 添加用户会默认添加账号
//		User sysUser = new SysUser();
//		User.setUserCode(param.getUserCode());
//		User.setCreateBy(param.getUserName());
//		User.add(sysUser);
		userService.save(param);
		userService.addCompute(param);
		String format = DateUtil.format(DateUtil.date(), "yyyy-MM");
		kpiPersonalService.deleteBysaveAccounting(format);
		return R.success("添加成功");
	}

	@PostMapping("/update")
	@ApiOperation(value = "编辑")
	@ApiOperationSupport(order = 3)
	public R update(@RequestBody User param) {
		userService.updateById(param);
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
		userService.removeByIds(param);
		return R.success("删除成功");
	}

	/**
	 * 修改密码
	 * @param userDTO
	 * @return
	 */
	@PostMapping(value = "/updatePwd")
	@ApiOperation("修改密码")
	@ApiOperationSupport(order = 5)
	public R upDataTechUser(@RequestBody SysUserPwdDto userDTO) {
		String Userid = String.valueOf(userDTO.getUserCode());
		SysUser userInfo = sysUserService.getById(userDTO.getUserCode());
		if(ObjectUtil.isEmpty(userInfo)){
			R.fail("没有此用户信息");
		}
		if (userInfo.getUserPwd().equals(ShiroUtils.enPas(userDTO.getUserPwd(), Userid))) {
			return R.fail(400, "与旧密码一样");
		}
		SysUser sysUser = new SysUser();
		sysUser.setUserCode(userDTO.getUserCode());
		sysUser.setUserPwd(ShiroUtils.enPas(userDTO.getUserPwd(), Userid));
		sysUserService.updateById(sysUser);
		return R.success("修改成功");
	}
}
