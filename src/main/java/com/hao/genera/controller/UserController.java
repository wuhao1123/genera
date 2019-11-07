package com.hao.genera.controller;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import com.hao.genera.support.Condition;
import com.hao.genera.support.Query;
import com.hao.genera.common.R;
import com.hao.genera.utils.Func;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hao.genera.entity.User;
import com.hao.genera.vo.UserVO;
import com.hao.genera.service.IUserService;

/**
 * 用户表 控制器
 *
 * @author teddy
 * @since 2019-11-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(description = "用户表相关接口")
public class UserController {

	private IUserService userService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入user")
	@RequiresPermissions("test:shiro")
	public R<User> detail(User user) {
		User detail = userService.getOne(Condition.getQueryWrapper(user));
		return R.data(detail);
	}

	/**
	 * 分页 用户表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "分页", notes = "传入user")
	@RequiresPermissions("test:shiro")
	public R<IPage<User>> list(User user, Query query) {
		IPage<User> pages = userService.page(Condition.getPage(query), Condition.getQueryWrapper(user));
		return R.data(pages);
	}

	/**
	 * 新增 用户表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入user")
	@RequiresPermissions("test:shiro")
	public R save(User user) {
		return R.data(userService.save(user));
	}

	/**
	 * 修改 用户表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入user")
	@RequiresPermissions("test:shiro")
	public R update( User user) {
		return R.data(userService.updateById(user));
	}

	/**
	 * 新增或修改 用户表
	 */
	@PutMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入user")
	@RequiresPermissions("test:shiro")
	public R submit(User user) {
		return R.data(userService.saveOrUpdate(user));
	}


	/**
	 * 删除 用户表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@RequiresPermissions("test:shiro")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(userService.deleteLogic(Func.toIntList(ids)));
	}

	@PostMapping("/login")
	@ApiOperation(value = "用户登录接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名",  paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String") })
	public R login( String username, String password) {
		return R.data(userService.login(username,password));
	}

}
