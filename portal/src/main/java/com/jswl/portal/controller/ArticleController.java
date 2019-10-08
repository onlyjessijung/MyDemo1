package com.jswl.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Article;
import com.jswl.portal.entity.User;
import com.jswl.portal.service.ArticleService;
import com.jswl.portal.util.ResultUtil;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	@Autowired
	private ArticleService as;
	
	@GetMapping("/toArticleManagePage")
	public String toArticleManagePage() {
		return "admin/article/article-manage";
	}

	@GetMapping("/toArticleAddPage")
	public String toArticleAddPage() {
		return "admin/article/article-add";
	}

	@GetMapping("/toArticleEditPage")
	public String toArticleEditPage(Model model,Integer id) {
		model.addAttribute("id", id);
		return "admin/article/article-edit";
	}

	@RequestMapping("/getDetailById")
	@ResponseBody
	public ResultDto<ArticleDto> getDetailById(Integer id){
		ArticleDto articleDto = as.getDetailById(id);
		return ResultUtil.success(articleDto);
	}
	
	@RequestMapping("/getListPage")
	@ResponseBody
	public ResultDto<Map<String, Object>> getListPage(Integer pageSize, Integer page) {
		Page<ArticleDto> listPage = as.getListPage(pageSize, page);
		Map<String, Object> map = new HashMap<>();
		map.put("total", listPage.getTotal());
		map.put("rows", listPage.getResult());
		return ResultUtil.success(map);
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResultDto<String> saveArticle(Article article, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return ResultUtil.fail("用户需要登录");
		}
		User user = (User) session.getAttribute("user");
		article.setStatus(1);
		article.setCreateTime(System.currentTimeMillis());
		article.setModifyTime(System.currentTimeMillis());
		article.setReleasTime(System.currentTimeMillis());
		article.setCreateUserId(user.getId());
		article.setModifyUserId(user.getId());
		Integer saveArticle = as.saveArticle(article);
		if (saveArticle != 0) {
			return ResultUtil.success("保存成功");
		} else {
			return ResultUtil.fail("保存失败");
		}
	}
//toArticleEditPage
	@PostMapping("/update")
	@ResponseBody
	public ResultDto<String> updateArticle(Article article) {
		Integer updateCount = as.updateArticleById(article);
		if (updateCount != 0) {
			return ResultUtil.success("更新成功");
		} else {
			return ResultUtil.fail("更新失败");
		}
	}
	
	@PostMapping("delByIds")
	@ResponseBody
	public ResultDto<String> deleteArticlesByIds(@RequestParam("ids[]")Integer[] ids){
		try {
			as.deleteArticlesByIds(ids);
			return ResultUtil.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("删除失败");
		}
	}

}
