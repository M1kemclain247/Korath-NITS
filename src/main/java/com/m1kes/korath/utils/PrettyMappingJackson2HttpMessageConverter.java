package com.m1kes.korath.utils;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class PrettyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {


    public PrettyMappingJackson2HttpMessageConverter() {
        super();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        setSupportedMediaTypes(Lists.newArrayList(new MediaType("application", "json+pretty", DEFAULT_CHARSET)));
    }
}
