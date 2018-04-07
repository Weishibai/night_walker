package com.sunshine.admin.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.sunshine.admin.service.IUserService;
import com.sunshine.common.util.AESCrypto;
import com.sunshine.common.vo.SaltToken;
import com.sunshine.common.vo.UserInfo;
import com.sunshine.dal.dao.IUserDao;
import com.sunshine.dal.entity.UserEntity;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

	private static final String AES_KEY = "lGPufzZxwKemIwmV";
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;

	@Override
	public Map<String, Object> getSession(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPwd(String uname, String pwd) {
		UserEntity user = userDao.queryUser(uname);
		if (StringUtils.isBlank(pwd)) {
			return false;
		}
		String pwdInDB = user.getPwd();
		String token = getToken(uname);
		String needMd5 = pwdInDB + token;
//		String pwdExpected = DigestUtils.md5Hex(needMd5);
		String pwdExpected = DigestUtils.md5Hex(DigestUtils.md5Hex(pwdInDB + user.getSalt()) + token);
		logger.debug("target pwd {} needMds: {}, pwdExpected:{}", pwd, needMd5, pwdExpected);
		return pwd.equals(pwdExpected);
	}

	public static void main(String[] args) {
		String pwdInDB = "Ml123456";
		String salt = "superadmin123";
		String token = "superadmin123";
		String test = DigestUtils.md5Hex(DigestUtils.md5Hex(pwdInDB + salt) + token);
		System.out.println(test.equalsIgnoreCase("2f5aab4b69dda6f0d21ce0f0e5e51cc2"));
	}

	private String getToken(String uname) {
		return uname + "123";
	}

	@Override
	public SaltToken getSaltToken(String uname) {
		SaltToken saltToken = new SaltToken();
		UserEntity user = userDao.queryUser(uname);
		saltToken.setSalt(user.getSalt());
		saltToken.setToken(getToken(uname));
		saltToken.setUname(uname);
		return saltToken;
	}

	@Override
	public boolean checkTicket(String uname, String ticket) {
		logger.debug("uname: {} ticket:{}", uname, ticket);
		if (StringUtils.isBlank(uname) || StringUtils.isBlank(ticket)) {
			return false;
		}
		String content = AESCrypto.decrypt_simple(ticket, AES_KEY);
		List<String> ticketList = Splitter.on("|").splitToList(content);
		String unameFromTicket = ticketList.get(0);
		String token = ticketList.get(1);
		long timestamp = Long.parseLong(ticketList.get(2));
		boolean flag = System.currentTimeMillis() - timestamp < 30 * 60 * 1000;
		return flag && uname.equals(unameFromTicket)
				&& "this is token".equals(token);
	}

	@Override
	public String genTicket(String uname) {
		List<String> list = new LinkedList<String>();
		list.add(uname);
		list.add("this is token");
		list.add(String.valueOf(System.currentTimeMillis()));
		String content = Joiner.on("|").join(list);
		String ticket = AESCrypto.encrypt_simple(content, AES_KEY);
		logger.debug("user:{} ticket:{}", uname, ticket);
		return ticket;
	}

	@Override
	public UserInfo queryUserInfo(String uname) {
		UserEntity entity = this.userDao.queryUser(uname);
		if (entity == null) {
			return null;
		}
		return UserInfo.convertFromEntity(entity);
	}
}
