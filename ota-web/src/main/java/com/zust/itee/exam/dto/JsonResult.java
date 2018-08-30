package com.zust.itee.exam.dto;

//封装json结果
public class JsonResult<T> {

	private boolean success;

	private T data;

	private String error;

	public JsonResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	/**
	 * 方便T为String的时候
	 *
	 * @param success
	 * @param data
	 * @param error
	 */
	public JsonResult(boolean success, T data, String error) {
		this.success = success;
		this.data = data;
		this.error = error;
	}

	public JsonResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public JsonResult() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
