/*
 * HelloTest.java 2022. 03. 18
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Donggeun.kim
 */
@SpringBootTest
@SpringBatchTest
@Slf4j
public class NaverD2ScrapingJobReaderTest {

	@Autowired
	NaverD2ScrapingReader naverD2ScrapingReader;

	@Test
	public void readPosts() throws Exception {

		naverD2ScrapingReader.doOpen();

		while(naverD2ScrapingReader.read()!=null){
			NaverD2Posts naverD2Posts = naverD2ScrapingReader.read();
			log.info("data={}", naverD2Posts);
		}
	}

}