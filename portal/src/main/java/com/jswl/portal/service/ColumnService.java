package com.jswl.portal.service;

import java.util.List;

import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.entity.Column;

public interface ColumnService {
	ColumnTreeNodeDto findTopColumnsAndArticle(Integer columnId,Integer limitNum);
	List<ColumnTreeNodeDto> findColumnAndArticle(Integer type);
	Integer hasChildColumn(Integer columnId);
	ColumnTreeNodeDto findHasChildColumn(Integer columnId);
	List<ColumnTreeNodeDto> findAllColumns();
	Integer saveOrUpdateColumn(Column column);
	Integer deleteColumn(Integer id);
}
