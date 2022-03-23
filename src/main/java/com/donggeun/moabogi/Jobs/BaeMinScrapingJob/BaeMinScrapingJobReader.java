/*
 * BaeMinScrapingJobReader.java 2022. 03. 23
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.BaeMinScrapingJob;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.donggeun.moabogi.Service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Donggeun.kim
 */
@Component
@RequiredArgsConstructor
@Slf4j
@StepScope
public class BaeMinScrapingJobReader extends AbstractItemCountingItemStreamItemReader<BaeMinPosts> {

	private final RestTemplate restTemplate;
	private final PostService postService;

	@Value("#{jobParameters['isFullScan']}")
	private String isFullScan;

	@Value("${baemin.posts.url}")
	private String url;

	@PostConstruct
	public void init() {
		setName("BaeMinScrapingReader");
	}

	@Override
	protected BaeMinPosts doRead() throws Exception {
		BaeMinPosts baeMinPosts = fetchPosts(getCurrentItemCount(), 10);
		return baeMinPosts;
	}

	@Override
	protected void doOpen() throws Exception {
		BaeMinPosts baeMinPosts = fetchPosts(0, 10);
		BaeMinPosts.Data data = Objects.requireNonNull(baeMinPosts).getData();
		BaeMinPosts.Data.Pagination pagination = data.getPagination();

		setMaxItemCount(pagination.getMax());
	}

	@Override
	protected void doClose() throws Exception {

	}

	/**
	 * 배민 기술블로그 리스트를 조회한다.
	 * @param currentIndex
	 * @param fetchSize
	 * @return
	 */
	private BaeMinPosts fetchPosts(int currentIndex, int fetchSize) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8));

		String requestBody = "action=get_posts_data"
			+ "&data[post][page]=" + currentIndex;

		HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
		ResponseEntity<BaeMinPosts> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
		});

		if (HttpStatus.OK != responseEntity.getStatusCode()) {
			log.error("http request error url: {}, requestBody : {}, header : {}", url, requestBody, httpHeaders);
			throw new RuntimeException("http request error");
		}

		return responseEntity.getBody();
	}
}