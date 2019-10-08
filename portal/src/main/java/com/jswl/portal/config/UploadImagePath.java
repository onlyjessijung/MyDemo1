package com.jswl.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 *   	图片映射地址配置
 * @author Administrator
 *
 */
@Configuration
public class UploadImagePath extends WebMvcConfigurerAdapter{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/admin/upload/**")//映射访问地址
		.addResourceLocations("file:D:/images/");
	}
}
