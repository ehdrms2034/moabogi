/*
 * RestTemplateConfiguration.java 2022. 03. 23
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Configuration;



import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Donggeun.kim
 */
@Configuration
public class RestTemplateConfiguration {


	@Bean
	public RestTemplate restTemplate(){
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		factory.setReadTimeout(30000);
		factory.setConnectionRequestTimeout(30000);

		HttpClient httpClient = HttpClientBuilder
			.create()
			.setMaxConnTotal(50)
			.setMaxConnPerRoute(10)
			.build();

		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}
}