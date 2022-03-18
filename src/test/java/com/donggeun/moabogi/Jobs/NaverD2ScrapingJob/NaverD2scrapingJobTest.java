/*
 * NaverD2scrapingJobTest.java 2022. 03. 19
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

/**
 * @author Donggeun.kim
 */
@SpringBootTest
@Import({NaverD2ScrapingJob.class})
public class NaverD2scrapingJobTest {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("naverD2ScrapJob")
	Job naverD2ScrapingJob;

	@Test
	@Rollback(value = false)
	public void doJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		jobLauncher.run(naverD2ScrapingJob, new JobParameters());
	}
}