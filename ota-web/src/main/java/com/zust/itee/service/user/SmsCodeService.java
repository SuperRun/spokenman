package com.zust.itee.service.user;

public interface SmsCodeService {
	public Boolean checkSmsCode(String phoneNum, int codeType, String smsCode);
	public Boolean checkPhoneNumberIsUsed(String phoneNum);
}
