package com.sunshine.dal.dao;

import com.sunshine.dal.entity.UserEntity;

public interface IUserDao {

	public UserEntity queryUser(String uname);

}
