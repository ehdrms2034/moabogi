/*
 * PostService.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.donggeun.moabogi.Model.Post;
import com.donggeun.moabogi.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Donggeun.kim
 */
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public void save(Post post){
		postRepository.save(post);
	}

	public void save(List<Post> post){
		postRepository.saveAll(post);
	}

	public List<Post> getPostByIds(List<Long> ids){
		if(CollectionUtils.isEmpty(ids)){
			return Collections.emptyList();
		}

		return postRepository.findPostsByIdIn(ids);
	}

	public List<Post> getPostsByWrittenDateTime(List<LocalDateTime> writtenTimes){
		if(CollectionUtils.isEmpty(writtenTimes)){
			return Collections.emptyList();
		}

		return postRepository.findPostsByWrittenDateTimeIn(writtenTimes);
	}

	public long getCount(){
		return postRepository.count();
	}

}