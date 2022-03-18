/*
 * PostRepository.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donggeun.moabogi.Model.Post;

/**
 * @author Donggeun.kim
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findPostsByIdIn(List<Long> ids);
}