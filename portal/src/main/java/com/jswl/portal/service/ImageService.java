package com.jswl.portal.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jswl.portal.entity.Image;

public interface ImageService {
	List<Image> selectAll();

	Page<Image> findAllImagesByPage(Integer pageSize, Integer page);

	Integer saveImage(Image image);

	void deleteImagesByIds(Integer[] ids);
}
