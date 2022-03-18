/*
 * NaverD2Posts.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Donggeun.kim
 */
@Data
public class NaverD2Posts {

	private List<Map<String, Object>> links;
	private List<Content> content;
	private Page page;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Content {
		private String postTitle;
		//image url
		private String postImage;
		//post description
		private String postHtml;

		private String url;

		private Date postPublishedAt;

		private int viewCount;

	}

	@Data
	public static class Page {
		private int size;
		private int totalElements;
		private int totalPages;
		private int number;
	}
}