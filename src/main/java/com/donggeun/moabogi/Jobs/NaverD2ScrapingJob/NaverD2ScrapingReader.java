/*
 * NaverD2Reader.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Donggeun.kim
 */
@Component
public class NaverD2ScrapingReader extends AbstractItemCountingItemStreamItemReader<NaverD2Posts> {

	private ObjectMapper objectMapper;

	@Value("${naverD2.posts.url}")
	private String NAVER_D2_POSTS_URL;

	@Value("${naverD2.posts.fetchsize}")
	private int NAVER_D2_FETCH_SIZE;

	@PostConstruct
	public void init() {
		setName("NaverD2ScrapingReader");

		objectMapper = new ObjectMapper();
		if (Strings.isEmpty(NAVER_D2_POSTS_URL)) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void doOpen() throws Exception {
		String url = String.format(NAVER_D2_POSTS_URL, 2, 0, NAVER_D2_FETCH_SIZE);
		NaverD2Posts d2Posts = objectMapper.readValue(new URL(url), NaverD2Posts.class);
		NaverD2Posts.Page pageInfo = d2Posts.getPage();

		setMaxItemCount(pageInfo.getTotalPages());
	}

	@Override
	protected NaverD2Posts doRead() throws Exception {
		String url = String.format(NAVER_D2_POSTS_URL, 2, getCurrentItemCount(), NAVER_D2_FETCH_SIZE);
		NaverD2Posts d2Posts = objectMapper.readValue(new URL(url), NaverD2Posts.class);
		return d2Posts;
	}

	@Override
	protected void doClose() throws Exception {

	}

}