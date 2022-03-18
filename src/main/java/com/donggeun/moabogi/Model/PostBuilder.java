/*
 * PostBuilder.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.donggeun.moabogi.Jobs.NaverD2ScrapingJob.NaverD2Posts;

/**
 * @author Donggeun.kim
 */
public class PostBuilder {

	public PostBuilder() {
		throw new RuntimeException("this is util class");
	}

	public static Post of(NaverD2Posts.Content naverD2Posts){
		Post post = new Post();
		post.setTitle(naverD2Posts.getPostTitle());
		post.setContent(naverD2Posts.getPostHtml());
		post.setCompanyType(CompanyType.NaverD2);
		post.setCreatedDateTime(LocalDateTime.now());
		post.setWrittenDateTime(LocalDateTime.ofInstant(naverD2Posts.getPostPublishedAt().toInstant(), ZoneId.of("Asia/Seoul")));
		post.setLinkUrl(naverD2Posts.getUrl());
		post.setImageUrl(naverD2Posts.getPostImage());

		return post;
	}
}