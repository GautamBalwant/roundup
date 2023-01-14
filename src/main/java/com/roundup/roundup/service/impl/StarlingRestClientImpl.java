package com.roundup.roundup.service.impl;

import com.roundup.roundup.service.StarlingRestClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * The class is used to make the Sandbox api calls
 */
@Service
public class StarlingRestClientImpl implements StarlingRestClient {

    private RestTemplate restTemplate;

    public StarlingRestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * The common method for the get calls
     *
     * @param uri
     * @param responseType
     * @param authorization
     * @param urlParams
     * @param <T>
     * @return
     */
    @Override
    public <T> ResponseEntity<T> getCall(final String uri, final ParameterizedTypeReference<T> responseType, final String authorization, final Map<String, String> urlParams) {
        HttpEntity<T> getEntity = new HttpEntity<>(createHttpHeaders(authorization));
  try {
     return restTemplate.exchange(uri,
              HttpMethod.GET, getEntity, responseType, urlParams);
  }catch (HttpClientErrorException | HttpServerErrorException e){
      throw e;
  }


    }

    /**
     * Common method for the post call
     *
     * @param uri
     * @param entity
     * @param responseType
     * @param urlParams
     * @param authorization
     * @param <T>
     * @return
     */
    @Override
    public <T> ResponseEntity<T> putCall(final String uri, final Object entity, final ParameterizedTypeReference<T> responseType, final Map<String, String> urlParams, final String authorization) {
        HttpEntity putEntity = new HttpEntity<>(entity, createHttpHeaders(authorization));
        return restTemplate.exchange(uri,
                HttpMethod.PUT, putEntity, responseType, urlParams);
    }

    /**
     * The method provides the request headers
     *
     * @param authorization
     * @return
     */
    public HttpHeaders createHttpHeaders(final String authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization);
        return headers;
    }
}
