package com.jswl.portal.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.entity.Article;

public interface ArticleService {
	List<Article> findArticleByColumnidLimit(Integer columnId,Integer limitNum);
	List<Article> findArticleByLike(String title);
	ArticleDto findArticleDtoById(Integer articleId);
	Article findPreArticle(Integer articleId);
	Article findNextArticle(Integer articleId);
	Page<ArticleDto> getListPage(Integer pageSize, Integer page);
	Integer saveArticle(Article article);
	Integer updateArticleById(Article article);
	ArticleDto getDetailById(Integer articleId);
	void deleteArticlesByIds(Integer[] ids);
}
