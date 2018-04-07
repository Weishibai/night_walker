package com.sunshine.admin.controller;

import java.io.IOException;

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

import com.sunshine.admin.service.IUserService;
import com.sunshine.common.http.response.CommonResponse;
import com.sunshine.common.util.VerifyCodeUtils;

@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(CaptchaController.class);

	@Autowired
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public void showCaptcha(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串：w
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 生成图片
		int w = 100, h = 30;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(),
					verifyCode);
		} catch (IOException e) {
			logger.error("error occurs when generate captcha", e);
		}
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse verify(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String code) {
		return new CommonResponse("verify_rt", "1");
	}
}
