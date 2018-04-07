package com.sunshine.admin.service;

import java.util.Map;

import com.sunshine.common.vo.SaltToken;
import com.sunshine.common.vo.UserInfo;

public interface IUserService {

	public Map<String, Object> getSession(String sid);

	public boolean checkPwd(String uname, String pwd);

	public SaltToken getSaltToken(String uname);

	public boolean checkTicket(String uname, String ticket);

	public String genTicket(String uname);

	public UserInfo queryUserInfo(String uname);
}
