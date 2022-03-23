/*
 * BaeMinPosts.java 2022. 03. 23
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.BaeMinScrapingJob;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Donggeun.kim
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaeMinPosts {

	private boolean success;
	private Data data;

	@lombok.Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Data {
		int length;
		Pagination pagination;
		List<Post> posts;

		@lombok.Data
		public static class Pagination {
			String current;
			int per;
			int max;
		}
	}

	@lombok.Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Post {
		boolean is_mig;
		Map<String, Object> author;
		String permalink;
		String date;
		String excerpt;
		ExtraInfo post;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@lombok.Data
	public static class ExtraInfo {
		private int ID;
		@JsonProperty("post_author")
		private String postAuthor;
		@JsonProperty("post_date")
		private String postDate;
		@JsonProperty("post_content")
		private String postContent;
		@JsonProperty("post_title")
		private String postTitle;
	}

}