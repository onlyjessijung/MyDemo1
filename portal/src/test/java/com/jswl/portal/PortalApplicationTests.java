package com.jswl.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jswl.portal.dto.ArticleDto;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.service.ArticleService;
import com.jswl.portal.service.ColumnService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalApplicationTests {

	@Autowired
	private ArticleService as;
	@Test
	public void contextLoads() {
		ArticleDto articleDto = as.getDetailById(5);
		System.out.println(articleDto);
		
	}

}
