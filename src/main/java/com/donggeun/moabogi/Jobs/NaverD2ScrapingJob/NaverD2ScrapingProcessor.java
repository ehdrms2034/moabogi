/*
 * NaverD2Processor.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.donggeun.moabogi.Model.Post;
import com.donggeun.moabogi.Model.PostBuilder;
import com.donggeun.moabogi.Service.PostService;

import lombok.RequiredArgsConstructor;

/**
 * @author Donggeun.kim
 */
@Component
@RequiredArgsConstructor
public class NaverD2ScrapingProcessor implements ItemProcessor<NaverD2Posts, List<Post>> {

	private final PostService postService;

	@Override
	public List<Post> process(NaverD2Posts item) {
		List<NaverD2Posts.Content> d2Posts = item.getContent();

		//d2Posts -> post
		List<Post> posts = d2Posts.stream()
			.map(PostBuilder::of)
			.peek(it -> it.setContent(
				it.getContent().substring(0, Math.min(it.getContent().length(), 256)))
			)
			.sorted(Comparator.comparing(Post::getWrittenDateTime))
			.collect(Collectors.toList());

		//중복 post 체크
		List<LocalDateTime> writtenDateTimeByPosts = posts.stream()
			.map(Post::getWrittenDateTime)
			.collect(Collectors.toList());

		List<Post> savedPost = postService.getPostsByWrittenDateTime(writtenDateTimeByPosts);

		//db에 저장된 post일경우 제외하고 chunk 이동
		return posts.stream()
			.filter(it -> !savedPost.contains(it))
			.collect(Collectors.toList());

	}


}