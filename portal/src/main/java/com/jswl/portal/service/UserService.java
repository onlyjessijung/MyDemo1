package com.jswl.portal.service;

import java.util.List;

import com.jswl.portal.dto.MenuTreeNodeDto;
import com.jswl.portal.entity.User;

public interface UserService {
	User findUserByUsernameAndPassword(String userCode,String password);
	List<MenuTreeNodeDto> findMenuByUserId(Integer userId);
}
