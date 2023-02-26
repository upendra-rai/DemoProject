package com.demo.project.dao;

import java.util.List;

import com.demo.project.models.one.to.one.uni.dir.User;

public interface CustomerUserDao {
	
	List<User>FindUserByName(String name);

}
