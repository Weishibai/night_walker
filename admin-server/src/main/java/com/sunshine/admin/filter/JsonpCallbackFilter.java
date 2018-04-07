package com.sunshine.admin.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonpCallbackFilter implements Filter {

	private static Logger logger = LoggerFactory
			.getLogger(JsonpCallbackFilter.class);
	private static final String badResponse = "{\"data\":{}, \"status\": 4, \"message\": \"illegal parameters\"}";

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");

		String callback = httpRequest.getParameter("callback");

		if (StringUtils.isNotBlank(callback)) {
			logger.debug("Wrapping response with JSONP callback '" + callback
					+ "'");
			if (callback.length() > 64) {
				// 返回错误, response写回bad response
				httpResponse.setContentType("application/json");
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.getWriter().print(badResponse);

				String url = httpRequest.getRequestURI() + "?"
						+ httpRequest.getQueryString();
				logger.warn("param callback length >64, url is: " + url);
				return;
			}

			OutputStream out = httpResponse.getOutputStream();

			GenericResponseWrapper wrapper = new GenericResponseWrapper(
					httpResponse);

			chain.doFilter(request, wrapper);

			// handles the content-size truncation
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			outputStream.write(new String(callback + "(").getBytes());
			outputStream.write(wrapper.getData());
			outputStream.write(new String(");").getBytes());
			byte jsonpResponse[] = outputStream.toByteArray();

			wrapper.setContentType("text/javascript;charset=UTF-8");
			wrapper.setContentLength(jsonpResponse.length);

			out.write(jsonpResponse);
			out.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}
}
