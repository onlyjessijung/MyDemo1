package com.jswl.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jswl.portal.dto.MenuTreeNodeDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.User;
import com.jswl.portal.service.UserService;
import com.jswl.portal.util.ResultUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService us;

	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/header")
	public String header() {
		return "admin/header";
	}

	@GetMapping("/left")
	public String left() {
		return "admin/left";
	}

	@GetMapping("/main")
	public String main() {
		return "admin/main";
	}

	@PostMapping("/doLogin")
	@ResponseBody
	public ResultDto<String> doLogin(String userCode, String password, HttpServletRequest request) {
		User user = us.findUserByUsernameAndPassword(userCode, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return ResultUtil.success("登录成功");
		} else {
			return ResultUtil.fail("用户名或密码错误");
		}
	}

	@RequestMapping("/getMenuByUserId")
	@ResponseBody
	public ResultDto<List<MenuTreeNodeDto>> getMenuByUserId(HttpSession session) {
		if (session.getAttribute("user") == null) {
			List<MenuTreeNodeDto> list = new ArrayList<>();
			ResultUtil.success(list);
		}
		User user = (User) session.getAttribute("user");
		Integer id = user.getId();
		return ResultUtil.success(us.findMenuByUserId(id));
	}

}
