package com.jswl.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ArticleService;
import com.jswl.portal.service.ColumnService;
import com.jswl.portal.service.ImageService;

@Controller
@RequestMapping("/front")
public class IndexController {
	@Autowired
	private ImageService is;
	@Autowired
	private ColumnService cs;
	@Autowired
	private ArticleService as;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@GetMapping("/index")
	public String showIndex(Model model) {
		List<Image> images = is.selectAll();
		List<Image> pic1List = new ArrayList<>();
		List<Image> pic2List = new ArrayList<>();
		List<Image> pic3List = new ArrayList<>();
		for (Image image : images) {
			Byte type = image.getType();
			switch (type) {
			case 1:
				pic1List.add(image);
				break;
			case 2:
				pic2List.add(image);
				break;
			case 3:
				pic3List.add(image);
				break;

			default:
				break;
			}
		}
		model.addAttribute("pic1List", pic1List);
		model.addAttribute("pic2List", pic2List);
		model.addAttribute("pic3List", pic3List);
		
		ColumnTreeNodeDto topColumn = cs.findTopColumnsAndArticle(19, 6);
		model.addAttribute("topColumn", topColumn);
		
		List<ColumnTreeNodeDto> mainColumnList = cs.findColumnAndArticle(2);
		model.addAttribute("mainColumnList", mainColumnList);
		
		List<ColumnTreeNodeDto> bottomColumnList = cs.findColumnAndArticle(3);
		model.addAttribute("bottomColumnList", bottomColumnList);
		
		
		return "front/index";
	}
	
	@PostMapping("/articleSearch")
	public String articleSearch(String word,Model model) {
		List<Article> list = as.findArticleByLike(word);
		model.addAttribute("list", list);
		return "front/article-search";
	}
	
	@GetMapping("/articleMore")
	public String articleMore(Integer columnId,Model model) {
		Integer count = cs.hasChildColumn(columnId);
		if (count.equals(0)) {
			ColumnTreeNodeDto column = cs.findTopColumnsAndArticle(columnId, null);
			model.addAttribute("column", column);
			return "front/article-more";
		} else {
			ColumnTreeNodeDto column = cs.findHasChildColumn(columnId);
			model.addAttribute("column", column);
			return "front/article-column";
		}
	}
	
	@GetMapping("/articleDetailById")
	public String articleDetailById(Integer articleId,Model model) {
		//根据文章id查询文章详细信息，并能够展示栏目信息
		ArticleDto detail = as.findArticleDtoById(articleId);
		Double score = stringRedisTemplate.opsForZSet().score("article-score", articleId+"");
		//证明没有点击过，那么需要设置初始值
		if (score==null) {
			stringRedisTemplate.opsForZSet().add("article-score", articleId+"",1);
			detail.setReadNum(1L);
		}else {
			score=score+1;
			stringRedisTemplate.opsForZSet().incrementScore("article-score", articleId+"",1);
			detail.setReadNum(score.longValue());
		}
		//上一篇
		model.addAttribute("pre", as.findPreArticle(articleId));
		//下一篇
		model.addAttribute("next", as.findNextArticle(articleId));
		//当前文章详情
		model.addAttribute("detail", detail);
		
		return "front/article-detail";
	}
}
