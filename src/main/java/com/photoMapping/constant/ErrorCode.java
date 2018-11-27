package com.photoMapping.constant;

public class ErrorCode {
	/** 接口调用成功 */
	public static final int SUCCESS = 200;
	/** 接口调用失败 */
	public static final int ERROR = 500;
	/** 接口请求失败 */
	public static final int ERROR_REQUEST = 400;
	/** 用户未登录 */
	public static final int ERROR_NOT_LOGIN = 510;
	/** 用户权限不足 */
	public static final int ERROR_PRIVILE_NO = 511;
	/** 还款失败 */
	public static final int LOAN_BACK_ERROR = 601;
	/** 还款处理中 */
	public static final int LOAN_BACK_PRODUCE = 602;

}
