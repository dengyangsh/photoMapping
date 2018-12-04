package com.photoMapping.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackWebUtil {

	private static Logger logger = LoggerFactory.getLogger(BackWebUtil.class);

	/**
	 * 异步方式 ，给 客户端写json数据
	 * 
	 * @param response
	 * @param msg
	 */
	public static void writeJsonToClient(HttpServletResponse response, String msg) {
		writeStr(response, msg, "application/json");
	}

	public static void writeJsonToClient(String code, String message, HttpServletResponse response) {
		writeStr(response, "{\"code\": \"" + code + "\",\"message\": \"" + message + "\"}", "application/json");
	}

	public static void writeStr(HttpServletResponse response, String msg, String type) {
		response.setContentType(type);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(msg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			logger.error("网络异常", e);
		}
	}

	/**
	 * 异步方式给客户端些 文本数据
	 * 
	 * @param response
	 * @param msg
	 */
	public static void writeStrToClient(HttpServletResponse response, String msg) {
		writeStr(response, msg, "text/html");
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
