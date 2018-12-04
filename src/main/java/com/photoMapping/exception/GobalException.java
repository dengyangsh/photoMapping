package com.photoMapping.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.photoMapping.constant.ErrorCode;
import com.photoMapping.util.Response;

@ControllerAdvice
public class GobalException {

	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseBody
	public Response dealExceptions(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		return Response.error(ErrorCode.ERROR, ex.getMessage());
	}

}
