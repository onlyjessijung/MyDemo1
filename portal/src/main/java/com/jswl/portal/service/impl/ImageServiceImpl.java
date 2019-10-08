package com.jswl.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jswl.portal.dao.ImageMapper;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageMapper im;

	public List<Image> selectAll() {
		List<Image> list = im.selectByExample(null);
		return list;
	}

	@Override
	public Page<Image> findAllImagesByPage(Integer pageSize, Integer page) {
		PageHelper.startPage(page, pageSize);
		List<Image> lists = im.selectByExample(null);
		Page<Image> pages = (Page<Image>)lists;
		return pages;
	}

	@Transactional
	@Override
	public Integer saveImage(Image image) {
		int insertCount = im.insertSelective(image);
		return insertCount;
	}

	@Transactional
	@Override
	public void deleteImagesByIds(Integer[] ids) {
		for (Integer id : ids) {
			im.deleteByPrimaryKey(id);
		}
	}

}
