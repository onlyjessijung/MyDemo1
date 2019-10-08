package com.jswl.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jswl.portal.dao.MenuMapper;
import com.jswl.portal.dao.UserMapper;
import com.jswl.portal.dto.MenuTreeNodeDto;
import com.jswl.portal.entity.Menu;
import com.jswl.portal.entity.User;
import com.jswl.portal.entity.UserExample;
import com.jswl.portal.entity.UserExample.Criteria;
import com.jswl.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um;
	@Autowired
	private MenuMapper mm;

	@Override
	public User findUserByUsernameAndPassword(String userCode, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserCodeEqualTo(userCode).andPasswordEqualTo(password);
		List<User> users = um.selectByExample(example);
		if (null != users && users.size() != 0) {
			return users.get(0);
		}
		return null;

	}

	@Override
	public List<MenuTreeNodeDto> findMenuByUserId(Integer userId) {
		List<MenuTreeNodeDto> dtos = new ArrayList<>();
		List<Menu> menus = mm.showMenuByUserId(userId);
		if (menus != null && menus.size() > 0) {
			for (Menu menu : menus) {
				if (menu.getPid() == 0) {
					MenuTreeNodeDto dto = new MenuTreeNodeDto();
					BeanUtils.copyProperties(menu, dto);
					dto.setChildNode(getChildMenuDtos(menu.getId(), menus));
					dtos.add(dto);
				}
			}
		}
		return dtos;
	}

	private List<MenuTreeNodeDto> getChildMenuDtos(Integer id, List<Menu> menus) {
		List<MenuTreeNodeDto> ChildMenuDtos = new ArrayList<>();
		for (Menu menu : menus) {
			if (menu.getPid() == id) {
				MenuTreeNodeDto ChildMenuDto = new MenuTreeNodeDto();
				BeanUtils.copyProperties(menu, ChildMenuDto);
				ChildMenuDto.setChildNode(getChildMenuDtos(menu.getId(), menus));
				ChildMenuDtos.add(ChildMenuDto);
			}
		}
		return ChildMenuDtos;
	}

}
