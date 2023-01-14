package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.transaction.TransactionResponse;
import com.roundup.roundup.service.StarlingRestClient;
import com.roundup.roundup.service.TransactionClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is used for the Transaction API calls
 */
@Service
public class TransactionClientImpl implements TransactionClient {

    @Value("${transaction.uri}")
    private String uri;

    private StarlingRestClient starlingRestClient;

    public TransactionClientImpl(StarlingRestClient starlingRestClient) {
        this.starlingRestClient = starlingRestClient;
    }

    /**
     * Get the transactions for specific account id
     *
     * @param accountUid
     * @param categoryId
     * @param authorization
     * @return
     */
    @Override
    public TransactionResponse getTransaction(final String accountUid, final String categoryId, final String authorization) {
        Map<String, String> urlParams = getUrlParams(accountUid, categoryId);
        return starlingRestClient.getCall(uri, new ParameterizedTypeReference<TransactionResponse>() {
        }, authorization, urlParams).getBody();
    }

    /**
     * Provides the request url params
     *
     * @param accountId
     * @param categoryUid
     * @return
     */
    private Map<String, String> getUrlParams(final String accountId, final String categoryUid) {
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("accountUid", accountId);
        urlParams.put("categoryUid", categoryUid);
        urlParams.put("value", getCurrentWeek());
        return urlParams;
    }

    /**
     * Provides the first day of the given week
     *
     * @return
     */
    private String getCurrentWeek() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime with = zonedDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return with.toString();
    }
}
