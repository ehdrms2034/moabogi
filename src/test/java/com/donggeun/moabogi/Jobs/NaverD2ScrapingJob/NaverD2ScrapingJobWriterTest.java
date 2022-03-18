/*
 * NaverD2ScrapingJobWriterTest.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.donggeun.moabogi.Model.CompanyType;
import com.donggeun.moabogi.Model.Post;

/**
 * @author Donggeun.kim
 */
@SpringBootTest
public class NaverD2ScrapingJobWriterTest {

	@Autowired
	NaverD2ScrapingWriter naverD2ScrapingWriter;

	@Test
	public void write() throws Exception {

		Post post = new Post();
		post.setTitle("title1");
		post.setContent("content1");
		post.setLinkUrl("http://www.content1.com");
		post.setWrittenDateTime(LocalDateTime.of(2022,1,1,0,0,0));
		post.setCreatedDateTime(LocalDateTime.of(2022,1,1,0,0,0));
		post.setUpdatedDateTime(LocalDateTime.of(2022,1,1,0,0,0));
		post.setAuthor(null);
		post.setCompanyType(CompanyType.NaverD2);
		post.setViewCount(0);

		naverD2ScrapingWriter.write(List.of(List.of(post)));
	}
}