package com.sunshine.admin.interceptor;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sunshine.admin.exception.ServerException;
import com.sunshine.admin.service.IUserService;
import com.sunshine.common.exception.ErrorMsg;


public class SessionCheckInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);

	@Resource
	private IUserService userService;

	@Resource
	private PropertiesFactoryBean propertiesFactoryBean;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = true;

		String requestURI = request.getRequestURI();
		logger.info("current request URI: {}", requestURI);
		String userLoginOpen = propertiesFactoryBean.getObject().getProperty("user.login", "true");
		if (StringUtils.equalsIgnoreCase(userLoginOpen, "true")) {
			if (!StringUtils.containsIgnoreCase(requestURI, "/admin-server/user/userinfo")) {
				return flag;
			}

			String ticket = getCookie(request, "ticket");
			String uname = getCookie(request, "uname");
			request.setAttribute("uname", uname);
			if (!userService.checkTicket(uname, ticket)) {
				response.getWriter().write("{\"status\": 0, \"data\": {}, \"errMsg\": \"error_need_login\"}");
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equalsIgnoreCase(cookie.getName())) {
					return cookie.getValue().trim();
				}
			}
		}
		return "";
	}

	private String genRandomSid() {
		String readable = UUID.randomUUID().toString();
		return readable;
	}

}
