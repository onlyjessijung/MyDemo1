package com.jswl.portal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ImageService;
import com.jswl.portal.util.ResultUtil;

@Controller
@RequestMapping("/admin/image")
public class ImageController {
	@Autowired
	private ImageService imageService;

	/**
	 * 跳转到图片管理页面
	 * 
	 * @return
	 */
	@GetMapping("/toImageManagePage")
	public String toImageManagePage() {

		return "admin/image/image-manage";
	}
	
	/**
	 * 跳转到添加图片界面
	 */
	@GetMapping("/toImageAddPage")
	public String toImageAddPage() {
		return "admin/image/image-add";
	}

	/**
	 * 获取文章列表，使用分页获取
	 * 
	 * @return
	 */
	@RequestMapping("/getListPage")
	@ResponseBody
	public ResultDto<Map<String, Object>> getListPage(Integer pageSize, Integer page) {
		Page<Image> pages = imageService.findAllImagesByPage(pageSize, page);
		// 由于前端分页需要 rows和total所以封装为map
		Map<String, Object> map = new HashMap<>();
		map.put("total", pages.getTotal());
		map.put("rows", pages.getResult());

		return ResultUtil.success(map);
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public ResultDto<String> uploadImage(MultipartFile file) {
		try {
			// 设置上传路径
			Long mi = System.currentTimeMillis();
			String path = "D:/images/" + mi + "/";
			File file1 = new File(path);
			// 如果没有该文件夹就创建
			if (!file1.exists()) {
				file1.mkdirs();
			}
			String originalName = file.getOriginalFilename();
			File trueFile = new File(path + originalName);
			file.transferTo(trueFile);
			String returnPath = "http://localhost:9090/admin/upload/" + mi + "/" + originalName;
			ResultDto<String> result = ResultUtil.success();
			result.setData(returnPath);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.fail("上传失败");
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResultDto<String> saveImage(Image image) {
		Integer count = imageService.saveImage(image);
		if (count > 0) {
			return ResultUtil.success("保存成功");
		}
		return ResultUtil.fail("保存失败");
	}

	@RequestMapping("/delByIds")
	@ResponseBody
	public ResultDto<String> delByIds(@RequestParam("ids[]") Integer[] ids) {
		if (ids != null && ids.length > 0) {
			try {
				imageService.deleteImagesByIds(ids);
				return ResultUtil.success("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtil.fail("删除失败");
			}
		}
		return ResultUtil.fail("删除失败 ids不合法");
	}

}
