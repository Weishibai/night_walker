package com.sunshine.admin.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunshine.admin.exception.ServerException;
import com.sunshine.admin.service.IUserService;
import com.sunshine.common.exception.ErrorMsg;
import com.sunshine.common.http.response.CommonResponse;
import com.sunshine.common.vo.SaltToken;
import com.sunshine.common.vo.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public CommonResponse login(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String uname,
			@RequestParam String pwd) {
		boolean flag = userService.checkPwd(uname, pwd);
		logger.debug("uname:{} pwd:{}, flag:{}", uname, pwd, flag);
		if (flag) {
			addCookie(response, "uname", uname, 3600);
			String ticket = userService.genTicket(uname);
			addCookie(response, "ticket", ticket, 3600);
			return new CommonResponse();
		} else {
			return new CommonResponse(new ServerException(
					ErrorMsg.ERROR_WRONG_USER_OR_PWD));
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		addCookie(response, "uname", " ", 3600);
		addCookie(response, "ticket", " ", 3600);
		response.sendRedirect("http://47.95.216.165/?#/login");
		return new CommonResponse();
	}

	@RequestMapping(value = "/prelogin", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse prelogin(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String uname) {
		SaltToken saltToken = userService.getSaltToken(uname);
		return new CommonResponse(saltToken);
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse userinfo(HttpServletRequest request,
			HttpServletResponse response) {
		String uname = (String) request.getAttribute("uname");
		UserInfo user = userService.queryUserInfo(uname);
		return new CommonResponse(user);
	}

	private void addCookie(HttpServletResponse response, String key, String value, int sec) {
		if (value == null || value.isEmpty())
			return;
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(sec);
		logger.debug("add cookie key:{} value:{} sec:{}", key, value, sec);
		response.addCookie(cookie);
	}

}
