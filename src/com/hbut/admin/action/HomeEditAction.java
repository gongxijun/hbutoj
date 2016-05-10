package com.hbut.admin.action;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.StreamHandler;


public class HomeEditAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String content;
	private String sidebar_content;

	public String getSidebar_content() {
		return sidebar_content;
	}

	public void setSidebar_content(String sidebarContent) {
		sidebar_content = sidebarContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String homeedit() throws Exception {

		try {
			String path = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("/");
			// System.out.println(path+"WEB-INF\\templates\\homepage.html");
			content = StreamHandler.read(path
					+ "WEB-INF\\templates\\homepage.html");
			sidebar_content = StreamHandler.read(path
					+ "WEB-INF\\templates\\sidebarex.html");
			// System.out.println(content);
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		return SUCCESS;
	}

}
