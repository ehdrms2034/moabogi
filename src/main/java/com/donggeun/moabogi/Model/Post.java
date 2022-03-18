/*
 * Post.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

/**
 * @author Donggeun.kim
 */
@Entity
@Data
public class Post {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CompanyType companyType;

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "varchar(2000)")
	private String content;

	private String author;

	private LocalDateTime writtenDateTime;

	@Column
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	@Column
	@CreationTimestamp
	private LocalDateTime updatedDateTime;

	@Column(nullable = false)
	private String linkUrl;

	private String imageUrl;

	private int viewCount;
}