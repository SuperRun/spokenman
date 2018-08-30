package com.zust.itee.exam.exception;

/**
 * 车企取消驾驶员报名异常
 * @author sdy
 *
 */
public class SignupCancelException extends RuntimeException{


	private static final long serialVersionUID = 7439562925123083839L;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public SignupCancelException(String msg) {
		super();
		this.msg = msg;
	}






}
