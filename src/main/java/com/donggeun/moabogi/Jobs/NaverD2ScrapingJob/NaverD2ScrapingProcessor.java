/*
 * NaverD2Processor.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.donggeun.moabogi.Model.Post;
import com.donggeun.moabogi.Model.PostBuilder;

/**
 * @author Donggeun.kim
 */
@Component
public class NaverD2ScrapingProcessor implements ItemProcessor<NaverD2Posts, List<Post>> {

	@Override
	public List<Post> process(NaverD2Posts item) {
		List<NaverD2Posts.Content> d2Posts = item.getContent();

		return d2Posts.stream()
			.map(PostBuilder::of)
			.peek(it -> it.setContent(
				it.getContent().substring(0, Math.min(it.getContent().length(), 256)))
			)
			.collect(Collectors.toList());
	}
}