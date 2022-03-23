/*
 * JacksonObjectMapper.java 2022. 03. 23
 *
 * Copyright 2022 NAVERCLOUD Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.donggeun.moabogi.Utils;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Donggeun.kim
 */
@Component
public class JacksonObjectMapper implements FactoryBean<ObjectMapper> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ObjectMapper getObject() throws Exception {
		return objectMapper;
	}

	@Override
	public Class<?> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}