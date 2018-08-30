package com.zust.itee.exam.exception;

/**
 * 找不到子车企时抛出的异常
 * @author sdy
 *
 */
public class NoSonCompanyException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 6395456787389173387L;

	private String msg;


	public NoSonCompanyException(String msg) {
		super();
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}





}
