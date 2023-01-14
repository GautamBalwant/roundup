package com.roundup.roundup.service;

import com.roundup.roundup.model.client.transaction.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransactionClient {

    TransactionResponse getTransaction(final String accountUid, final String categoryId, final String authorization);
}
