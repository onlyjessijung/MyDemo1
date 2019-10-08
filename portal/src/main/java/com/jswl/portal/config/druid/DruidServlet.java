package com.jswl.portal.config.druid;

import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = "/druid/*")
public class DruidServlet extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4805386232245633048L;

}
