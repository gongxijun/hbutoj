package com.hbut.tags.action;

import java.sql.Timestamp;

import com.hbut.tags.service.TagsService;
import com.hbut.tags.vo.Tags;
import com.opensymphony.xwork2.ActionSupport;

public class TagsAction extends ActionSupport {

	private TagsService tagsService;
	private String tagsName;
	private Tags tags;
	private Integer tagId;

	public String saveTags() throws Exception {

		try {
			Tags tags_ = new Tags();
			tags_.setIndate(new Timestamp(System.currentTimeMillis()));
			tags_.setName(tagsName);
			tagsService.save(tags_);
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		return SUCCESS;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public void setTagsService(TagsService tagsService) {
		this.tagsService = tagsService;
	}

	public TagsService getTagsService() {
		return tagsService;
	}

	public String getTagsName() {
		return tagsName;
	}

	public void setTagsName(String tagsName) {
		this.tagsName = tagsName;
	}

	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}

}
