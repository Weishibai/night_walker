package com.sunshine.admin.controller;

import java.rmi.ServerException;
import java.util.zip.ZipException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sunshine.common.exception.ErrorMsg;
import com.sunshine.common.http.response.CommonResponse;

public abstract class BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	protected CommonResponse handleException(Exception ex,
			HttpServletRequest request) {
		logger.error("error occurs", ex);
		if (!(ex instanceof ServerException)) {
			ex = new ServerException(ErrorMsg.ERROR_UNKNOWN_ERR);
		}
		CommonResponse res = new CommonResponse((ServerException) ex);
		return res;
	}
}
