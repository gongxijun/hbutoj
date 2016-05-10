package com.hbut.common.action;

import java.util.HashMap;
import java.util.Map;
import com.hbut.bean.OnlineUserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.util.OnlineUsers;

public class JsonOnlineUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552864426020089250L;

	private boolean success;
	private String error;
	private Map<String, OnlineUserBean> online_users;

	public Map<String, OnlineUserBean> getOnline_users() {
		return online_users;
	}

	public void setOnline_users(Map<String, OnlineUserBean> onlineUsers) {
		online_users = onlineUsers;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String onlineUsers() throws Exception {
		try {
			//
			online_users = new HashMap<String, OnlineUserBean>();
			online_users = OnlineUsers.getOnlineUsers();
			// System.out.println(usersList.size());
			success = true;
			return SUCCESS;

		} catch (Exception e) {
			// TODO: handle exception
			success = false;
			error = "get online users error.";
			return SUCCESS;
		}
	}
}
