/*
 * NaverD2ScrapingJob.java 2022. 03. 19
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Jobs.NaverD2ScrapingJob;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.donggeun.moabogi.Model.Post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Donggeun.kim
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class NaverD2ScrapingJob {

	private final NaverD2ScrapingReader naverD2ScrapingReader;
	private final NaverD2ScrapingProcessor naverD2ScrapingProcessor;
	private final NaverD2ScrapingWriter naverD2ScrapingWriter;

	private final StepBuilderFactory stepBuilderFactory;
	private final JobBuilderFactory jobBuilderFactory;

	@Bean
	public Step naverD2ScrapingStep(){
		return stepBuilderFactory.get("naverD2ScrapingStep")
			.<NaverD2Posts, List<Post>>chunk(10)
			.reader(naverD2ScrapingReader)
			.processor(naverD2ScrapingProcessor)
			.writer(naverD2ScrapingWriter)
			.build();
	}

	@Bean("naverD2ScrapJob")
	public Job naverD2ScrapJob(){
		return jobBuilderFactory.get("naverD2ScrapJob")
			.start(naverD2ScrapingStep())
			.listener(new JobExecutionListener() {
				@Override
				public void beforeJob(JobExecution jobExecution) {
					log.info("============ Start executed NaverD2Scraping Job ==========");
				}

				@Override
				public void afterJob(JobExecution jobExecution) {
					log.info("============ Successful executed NaverD2Scraping Job ==========");
				}
			})
			.build();
	}


}