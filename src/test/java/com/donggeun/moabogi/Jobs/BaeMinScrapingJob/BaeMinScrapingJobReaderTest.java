/*
 * BaeMinScrapingJob.java 2022. 03. 23
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.BaeMinScrapingJob;

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
public class BaeMinScrapingJobReaderTest {

	@Autowired
	BaeMinScrapingJobReader baeMinScrapingJobReader;

	@Test
	public void doOpen() throws Exception {
		baeMinScrapingJobReader.doOpen();

		while (baeMinScrapingJobReader.read() != null) {
			BaeMinPosts baeMinPosts = baeMinScrapingJobReader.read();
			log.info("data={}", baeMinPosts);
		}
	}

}