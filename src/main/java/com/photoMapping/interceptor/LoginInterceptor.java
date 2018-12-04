package com.photoMapping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.photoMapping.constant.ErrorCode;
import com.photoMapping.util.BackWebUtil;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		String requestUri = req.getRequestURL().toString();
		
		if (requestUri.indexOf("register") > 0 || requestUri.indexOf("login") > 0) {
			return true;
		}

		Object user = req.getSession().getAttribute("user");
		if (user != null) {
			return true;
		}

		BackWebUtil.writeJsonToClient(ErrorCode.ERROR_NOT_LOGIN + "", "请先登录", res);
		return false;
	}

}
