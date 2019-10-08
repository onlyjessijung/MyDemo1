package com.jswl.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.dto.ResultDto;
import com.jswl.portal.entity.Column;
import com.jswl.portal.service.ColumnService;
import com.jswl.portal.util.ResultUtil;

@Controller
@RequestMapping("/admin/column")
public class ColumnController {

	@Autowired
	private ColumnService cs;

	@GetMapping("/toColumnManagePage")
	public String toColumnManagePage() {
		return "admin/column/column-manage";
	}

	@RequestMapping("/getColumnTreeList")
	@ResponseBody
	public ResultDto<List<ColumnTreeNodeDto>> getColumnTreeList() {
		return ResultUtil.success(cs.findAllColumns());
	}

	@RequestMapping("/saveColumn")
	@ResponseBody
	public ResultDto<Integer> saveColumn(Column column) {
		Integer id = cs.saveOrUpdateColumn(column);
		return ResultUtil.success(id);
	}

	@RequestMapping("/delColumn")
	@ResponseBody
	public ResultDto<String> delColumn(Integer columnId) {
		Integer result = cs.deleteColumn(columnId);
		if (result != 0) {
			return ResultUtil.success("删除数据成功");
		} else {
			return ResultUtil.success("删除数据失败");
		}
	}
}
