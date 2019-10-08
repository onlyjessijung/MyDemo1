package com.jswl.portal.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jswl.portal.dao.ArticleMapper;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.ArticleExample;
import com.jswl.portal.entity.ArticleExample.Criteria;
import com.jswl.portal.entity.Column;
import com.jswl.portal.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper am;

	@Override
	public List<Article> findArticleByColumnidLimit(Integer columnId, Integer limitNum) {
		return am.showArticleByColumnidLimit(columnId, limitNum);
	}

	@Override
	public List<Article> findArticleByLike(String title) {
		return am.showArticleByLike(title);
	}

	@Override
	public ArticleDto findArticleDtoById(Integer articleId) {
		Article article = am.selectByPrimaryKey(articleId);
		ArticleDto articleDto = new ArticleDto();
		BeanUtils.copyProperties(article, articleDto);
		Column column = new Column();
		articleDto.setColumnCode(column.getCode());
		articleDto.setColumnTitle(column.getName());
		return articleDto;
	}

	public Article findPreArticle(Integer articleId) {
		return am.showPreArticle(articleId);
	}

	public Article findNextArticle(Integer articleId) {
		return am.showNextArticle(articleId);
	}

	@Override
	public Page<ArticleDto> getListPage(Integer pageSize, Integer page) {
		PageHelper.startPage(page, pageSize);
		Page<ArticleDto> showListPage = (Page<ArticleDto>) am.showListPage();
		return showListPage;
	}

	@Transactional
	@Override
	public Integer saveArticle(Article article) {
		int insertSelective = am.insertSelective(article);
		return insertSelective;
	}

	@Transactional
	@Override
	public Integer updateArticleById(Article article) {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(article.getId());
		int update = am.updateByExampleSelective(article, example);
		return update;

	}

	@Override
	public ArticleDto getDetailById(Integer articleId) {
		List<ArticleDto> list = am.showListPage();
		for (ArticleDto articleDto : list) {
			if (articleDto.getId() == articleId) {
				return articleDto;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteArticlesByIds(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				am.deleteByPrimaryKey(id);
			}
		}
	}

}
