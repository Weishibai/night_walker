package com.sunshine.publicserver.service.markup.util;

import com.alibaba.fastjson.JSON;
import com.sunshine.publicserver.service.markup.pojo.HttpVo;
import com.sunshine.publicserver.service.markup.pojo.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewWhiteListURL {

	private static Logger LOGGER = LoggerFactory.getLogger(NewWhiteListURL.class);

	public static String http(String params, String url) {
		String respStr;
		HttpVo httpvo = new HttpVo();
		httpvo.setContentType("text/plain");
		httpvo.setData(params);
		httpvo.setEncode("utf-8");
		httpvo.setMethods("POST");
		httpvo.setTimeout(50 * 1000l);
		HttpClientUtil httpurl = new HttpClientUtil();
		try {
			httpurl.setConnectTimeout(60 * 1000);
			respStr = httpurl.doPost(url, JSON.toJSONString(httpvo));
			if (StringUtils.isNotEmpty(respStr)) {
				ResponseVo resp = JSON.parseObject(respStr, ResponseVo.class);
				if ("true".equals(resp.getRequestResult())) {
					return resp.getResponseBody();
				} else {
					LOGGER.error("接口返回异常: {}", resp.getResponseBody());
					return null;
				}
			}
		} catch (Exception e) {
			LOGGER.error("markup interface error: ", e);
		}
		return null;
	}
}
