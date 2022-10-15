package org.springblade.modules.system.controller;

import cn.hutool.extra.tokenizer.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.dto.SysUserDto;
import org.springblade.modules.system.dto.SysUserE;
import org.springblade.modules.system.entity.SysUser;
import org.springblade.modules.system.service.SysUserService;
import org.springblade.modules.system.vo.UserInfo;
import org.springblade.modules.user.service.ShiroService;
import org.springblade.modules.user.service.TechuserAccessHistoryService;
import org.springblade.modules.user.tool.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Author Ja'c'k
 * @Dato 2022/6/13 9:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/in")
@Api(tags = "登录登出")
@CrossOrigin
public class LoginController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private ShiroService shiroService;

	@Autowired
	TechuserAccessHistoryService techuserAccessHistoryService;

	/**
	 * 登陆
	 *
	 * @return 退出成功
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation("登录")
	public R login(@RequestBody SysUserDto user) {
		//用户信息
		SysUser userInfo = sysUserService.getById(user.getUserCode());
		//账号不存在
		if (userInfo == null) {
			return  R.fail("账号错误");
		}
		//密码错误
		if (!userInfo.getUserPwd().equals(ShiroUtils.enPas(user.getUserPwd(), String.valueOf(user.getUserCode())))) {
			return  R.fail("密码错误");
		}
		//账号状态
		if (userInfo.getIsDeleted()==1 || userInfo.getIsDeleted() ==null) {
			return  R.fail("账号已被删除");
		}

		//生成token，并保存到数据库
		String token = shiroService.createToken(user.getUserCode());
		UserInfo info = new UserInfo();
		info.setToken(token);
		info.setUserCode(userInfo.getUserCode());
		info.setCreateBy(userInfo.getCreateBy());

		//登录的信息插入到时间记录表
//		techuserAccessHistoryService.instLogin(user);
		//修改用户信息
//		sysUserService.updataLogin(user);
		return R.data(info);
	}

	/**
	 * 登出
	 *
	 * @return 退出成功
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiOperation("登出")
	public R logout(@RequestBody SysUser user) {
		shiroService.logout(user.getUserCode());

//		techuserAccessHistoryService.instLogout(user);

		//修改用户信息
//		sysUserService.updataLogout(user);

		return R.success("退出成功");
	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	@ApiOperation("注册")
	public R enroll(@RequestBody SysUserE user) {
		if (user.getUserCode() == null || user.getUserPwd() == null) {
			return R.fail(200, "请传入登录账号和密码");
		}
		return sysUserService.enroll(user);

	}
//	@RequestMapping("/uploadFile")
//	public Result uploadFile(HttpServletRequest request, MultipartFile file){
//
//		return fileUploadService.uploadFile(request, file);
//
//	}

}

