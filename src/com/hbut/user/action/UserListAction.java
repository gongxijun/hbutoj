package com.hbut.user.action;

import java.util.List;

import com.hbut.user.service.UserService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class UserListAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private List<String> users;
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	@JSON(deserialize = false, serialize = false)
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String execute() throws Exception {

		String q_ = new String();
		if (q != null) {
			q_ = q;
		} else {
			return SUCCESS;
		}
		String sql = "select u.username from User u where u.username like ? and u.defunct='N' order by u.id ASC";
		// System.out.println(q_);

		users = userService.queryAllUserName(sql, q_, 10);
	
		return SUCCESS;
	}

}
