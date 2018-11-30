package com.photoMapping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.photoMapping.model.User;

/**
 * ClassName: BaseController <br/>
 * Function: 基础controller. <br/>
 * date: 2018年3月23日 下午4:27:50 <br/>
 * 
 * @author hejiangfeng@hebao.im
 * @version
 * @since JDK 1.8
 */
public abstract class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 获取登陆用户. <br/>
	 * 
	 * @author hejiangfeng@hebao.im
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	public User getLoginUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("user");
		if (obj != null) {
			return (User) obj;
		}
		return null;
	}

}
