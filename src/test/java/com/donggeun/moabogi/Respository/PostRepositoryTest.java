/*
 * PostRepositoryTest.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Respository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.donggeun.moabogi.Model.CompanyType;
import com.donggeun.moabogi.Model.Post;
import com.donggeun.moabogi.Repository.PostRepository;

/**
 * @author Donggeun.kim
 */
@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepository;

	private Post createMock(){
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
		return post;
	}

	@Test
	public void save(){
		Post post = createMock();
		postRepository.save(post);
	}

	@Test
	public void get(){
		Post post = postRepository.save(createMock());

		Assertions.assertNotEquals(0, post.getId());

		Post fetchedPost = postRepository.getById(post.getId());

		Assertions.assertEquals(post.getId(), fetchedPost.getId());


	}

}