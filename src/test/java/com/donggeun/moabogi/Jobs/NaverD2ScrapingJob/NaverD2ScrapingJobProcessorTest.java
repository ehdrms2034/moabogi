/*
 * NaverD2ScrapingJobProcessor.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.donggeun.moabogi.Model.Post;

/**
 * @author Donggeun.kim
 */
@SpringBootTest
public class NaverD2ScrapingJobProcessorTest {

	@Autowired
	NaverD2ScrapingProcessor naverD2ScrapingProcessor;


	@Test
	public void process() throws Exception {

		NaverD2Posts naverD2Posts = new NaverD2Posts();

		NaverD2Posts.Content content1 = new NaverD2Posts.Content();
		content1.setPostTitle("title1");
		content1.setPostHtml("content1");
		content1.setUrl("http://test1.com");
		content1.setPostPublishedAt(DateUtil.parse("2020-01-01"));

		naverD2Posts.setContent(List.of(content1));

		List<Post> posts = naverD2ScrapingProcessor.process(naverD2Posts);
		Assertions.assertEquals(1, posts.size());

		Post post = posts.get(0);
		Assertions.assertEquals("title1", post.getTitle());
		Assertions.assertEquals("content1", post.getContent());
		Assertions.assertEquals("http://test1.com", post.getLinkUrl());
		Assertions.assertEquals(LocalDateTime.of(2020,1,1,0,0,0), post.getWrittenDateTime());

	}

}