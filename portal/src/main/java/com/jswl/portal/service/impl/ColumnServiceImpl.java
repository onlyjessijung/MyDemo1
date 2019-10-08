package com.jswl.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jswl.portal.dao.ArticleMapper;
import com.jswl.portal.dao.ColumnMapper;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.Column;
import com.jswl.portal.service.ColumnService;

@Service
public class ColumnServiceImpl implements ColumnService {
	@Autowired
	private ArticleMapper am;
	@Autowired
	private ColumnMapper cm;

	// 查询顶部文章的方法
	public ColumnTreeNodeDto findTopColumnsAndArticle(Integer columnId, Integer limitNum) {

		Column column = cm.selectByPrimaryKey(columnId);
		ColumnTreeNodeDto columnTreeNodeDto = new ColumnTreeNodeDto();
		BeanUtils.copyProperties(column, columnTreeNodeDto);

		List<ArticleDto> articleDtos = new ArrayList<>();
		List<Article> articles = am.showArticleByColumnidLimit(columnId, limitNum);
		for (Article article : articles) {
			ArticleDto articleDto = new ArticleDto();
			BeanUtils.copyProperties(article, articleDto);
			articleDto.setColumnCode(column.getCode());
			articleDto.setColumnTitle(column.getName());
			articleDtos.add(articleDto);
		}

		columnTreeNodeDto.setArticleList(articleDtos);
		return columnTreeNodeDto;

	}

	public List<ColumnTreeNodeDto> findColumnAndArticle(Integer type) {
		List<ColumnTreeNodeDto> dtos = new ArrayList<>();
		List<Column> columns = cm.findColumnByType(2);
		for (Column column : columns) {
			if (column.getPid() == 0) {
				ColumnTreeNodeDto dto = new ColumnTreeNodeDto();
				BeanUtils.copyProperties(column, dto);
				List<ArticleDto> dtos2 = new ArrayList<>();
				List<Article> articles = am.showArticleByColumnidLimit(column.getId(), 6);
				for (Article article : articles) {
					ArticleDto dto2 = new ArticleDto();
					BeanUtils.copyProperties(article, dto2);
					dto2.setColumnCode(column.getCode());
					dto2.setColumnTitle(column.getName());
					dtos2.add(dto2);
				}
				dto.setArticleList(dtos2);
				dto.setChildNode(getChildColumnDtos(column.getId(), columns));
				dtos.add(dto);
			}
		}
		return dtos;
	}

	private List<ColumnTreeNodeDto> getChildColumnDtos(Integer id, List<Column> columns) {
		List<ColumnTreeNodeDto> dtos = new ArrayList<>();
		for (Column column : columns) {
			if (column.getPid() == id) {
				ColumnTreeNodeDto dto = new ColumnTreeNodeDto();
				BeanUtils.copyProperties(column, dto);
				List<ArticleDto> dtos2 = new ArrayList<>();
				List<Article> articles = am.showArticleByColumnidLimit(column.getId(), 6);
				for (Article article : articles) {
					ArticleDto dto2 = new ArticleDto();
					BeanUtils.copyProperties(article, dto2);
					dto2.setColumnCode(column.getCode());
					dto2.setColumnTitle(column.getName());
					dtos2.add(dto2);
				}
				dto.setArticleList(dtos2);
				dto.setChildNode(getChildColumnDtos(column.getId(), columns));
				dtos.add(dto);
			}
		}
		return dtos;
	}

	public Integer hasChildColumn(Integer columnId) {
		return cm.countColumn(columnId);
	}

	public ColumnTreeNodeDto findHasChildColumn(Integer columnId) {
		ColumnTreeNodeDto dto = new ColumnTreeNodeDto();
		Column column = cm.selectByPrimaryKey(columnId);
		BeanUtils.copyProperties(column, dto);
		List<ArticleDto> listArticleDto = new ArrayList<>();
		List<Article> listArticle = am.showArticleByColumnidLimit(columnId, null);
		for (Article article : listArticle) {
			ArticleDto articleDto = new ArticleDto();
			BeanUtils.copyProperties(article, articleDto);
			articleDto.setColumnCode(column.getCode());
			articleDto.setColumnTitle(column.getName());
			listArticleDto.add(articleDto);
		}
		dto.setArticleList(listArticleDto);
		List<Column> columns = cm.selectByExample(null);
		dto.setChildNode(getChildColumnDtos(column.getId(), columns));
		return dto;
	}

	@Override
	public List<ColumnTreeNodeDto> findAllColumns() {
		List<Column> columns = cm.selectByExample(null);
		List<ColumnTreeNodeDto> dtos = new ArrayList<>();
		for (Column c : columns) {
			// 如果是顶级栏目
			if (c.getPid() == 0) {
				ColumnTreeNodeDto childDto = new ColumnTreeNodeDto();
				BeanUtils.copyProperties(c, childDto);
				childDto.setChildNode(getChildrenColumnDtos(c.getId(), columns));
				dtos.add(childDto);
			}
		}
		return dtos;
	}

	private List<ColumnTreeNodeDto> getChildrenColumnDtos(Integer id, List<Column> columns) {
		List<ColumnTreeNodeDto> dtos = new ArrayList<>();
		for (Column c : columns) {
			if (c.getPid() == id) {
				ColumnTreeNodeDto childDto = new ColumnTreeNodeDto();
				BeanUtils.copyProperties(c, childDto);
				childDto.setChildNode(getChildrenColumnDtos(c.getId(), columns));
				dtos.add(childDto);
			}
		}
		return dtos;
	}

	@Transactional
	@Override
	public Integer saveOrUpdateColumn(Column column) {
		if (column != null) {
			Integer id = column.getId();
			if (id != null) {
				cm.updateByPrimaryKeySelective(column);
				return id;
			} else {
				cm.insertSelective(column);
				Integer maxId = cm.maxId();
				return maxId;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public Integer deleteColumn(Integer id) {
		return cm.deleteByPrimaryKey(id);
	}

}
