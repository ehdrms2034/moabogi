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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.donggeun.moabogi.Service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Donggeun.kim
 */
@Component
@RequiredArgsConstructor
@Getter @Setter
@StepScope
public class NaverD2ScrapingReader extends AbstractItemCountingItemStreamItemReader<NaverD2Posts> {

	@Value("#{jobParameters['isFullScan']}")
	private String isFullScan;

	private ObjectMapper objectMapper;

	@Value("${naverD2.posts.url}")
	private String NAVER_D2_POSTS_URL;

	@Value("${naverD2.posts.fetchsize}")
	private int NAVER_D2_FETCH_SIZE;

	private int lastPageCount;

	private final PostService postService;

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
		setLastPageCount(pageInfo.getTotalPages());

		//풀 스캔이 아닌 경우, DB에 없는 부분부터 갱신 시킨다.
		if (!isFullScan()) {
			long totalPostCount = d2Posts.getPage().getTotalElements();
			long savedPostCount = postService.getCount();

			int skipedPageCount = (int)((totalPostCount - savedPostCount) / NAVER_D2_FETCH_SIZE);
			setCurrentItemCount(skipedPageCount);
		}
	}

	@Override
	protected NaverD2Posts doRead() throws Exception {
		String url = String.format(NAVER_D2_POSTS_URL, 2, getLastPageCount() - getCurrentItemCount() - 1, NAVER_D2_FETCH_SIZE);
		NaverD2Posts d2Posts = objectMapper.readValue(new URL(url), NaverD2Posts.class);
		return d2Posts;
	}

	@Override
	protected void doClose() throws Exception {

	}

	/**
	 * 네이버 D2 포스트 스크래핑 정책 결정
	 * @return true : 처음부터 끝까지 스캔 , false : 마지막 페이지만 읽어들인다.
	 */
	private boolean isFullScan() {
		return StringUtils.isNotEmpty(isFullScan) && Boolean.parseBoolean(isFullScan);
	}

}