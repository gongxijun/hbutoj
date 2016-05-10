package com.hbut.contest.attend.action;

import java.util.Date;
import com.hbut.contest.attend.service.AttendService;
import com.hbut.contest.attend.vo.Attend;
import com.hbut.contest.service.ContestService;
import com.hbut.contest.vo.Contest;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UnRegisterContestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AttendService attendService;
	private ContestService contestService;
	private Integer contestId = 0;
	private Contest contest;

	public String UnRegisterContest() throws Exception {

		try {
			String username = (String) ActionContext.getContext().getSession()
					.get("session_username");
			if (null == username) {
				return LOGIN;
			}
			Contest contest_ = new Contest();
			contest_ = contestService.queryContest(contestId, "USER");
			if (null == contest_) {
				// No such contest;
				return ERROR;
			}

			if (contest_.getStart_time().before(new Date())) {
				return ERROR;
			}

			Attend attend_ = new Attend();
			attend_ = attendService.queryUserAttend(contestId, username);
			if (attend_ == null) {
				return ERROR;
			}
			attendService.delete(attend_);

		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		return SUCCESS;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public AttendService getAttendService() {
		return attendService;
	}

	public void setAttendService(AttendService attendService) {
		this.attendService = attendService;
	}

	public ContestService getContestService() {
		return contestService;
	}

	public void setContestService(ContestService contestService) {
		this.contestService = contestService;
	}

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

}
