package com.roundup.roundup.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface StarlingRestClient {

    <T> ResponseEntity<T> getCall(final String uri, final ParameterizedTypeReference<T> responseType, final String authorization, final Map<String, String> urlParams);

    <T> ResponseEntity<T> putCall(final String uri, final Object entity, final ParameterizedTypeReference<T> responseType, final Map<String, String> urlParams, final String authorization);
}
