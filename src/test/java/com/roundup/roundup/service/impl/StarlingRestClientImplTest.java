package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StarlingRestClientImplTest {

    private StarlingRestClientImpl starlingRestClientImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    ResponseEntity<Object> response;

    @Test
    public void getCallTest(){
        starlingRestClientImpl=new StarlingRestClientImpl(restTemplate);
        when(restTemplate.exchange(anyString(),any(HttpMethod.class),any(HttpEntity.class),any(ParameterizedTypeReference.class),anyMap())).thenReturn(response);

        ResponseEntity<Object> result=  starlingRestClientImpl.getCall("test/api",new ParameterizedTypeReference<Object>(){},"Bearer ",new HashMap<String, String>());
        assertEquals(result,response);
    }

    @Test
    public void putCallTest(){
        starlingRestClientImpl=new StarlingRestClientImpl(restTemplate);
        when(restTemplate.exchange(anyString(),any(HttpMethod.class),any(HttpEntity.class),any(ParameterizedTypeReference.class),anyMap())).thenReturn(response);
        ResponseEntity<Object> result=  starlingRestClientImpl.putCall("test/api",new Object(),new ParameterizedTypeReference<Object>(){},new HashMap<String, String>(),"Bearer ");
        assertEquals(result,response);
    }
}
