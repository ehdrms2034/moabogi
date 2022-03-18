/*
 * NaverD2ScrapingWriter.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.donggeun.moabogi.Model.Post;
import com.donggeun.moabogi.Service.PostService;

import lombok.RequiredArgsConstructor;

/**
 * @author Donggeun.kim
 */
@Component
@RequiredArgsConstructor
public class NaverD2ScrapingWriter implements ItemWriter<List<Post>> {

	private final PostService postService;

	@Override
	public void write(List<? extends List<Post>> items) throws Exception {
		List<Post> posts = items.stream()
			.flatMap(List::stream)
			.collect(Collectors.toList());

		postService.save(posts);
	}
}