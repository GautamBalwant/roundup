package com.roundup.roundup.service.impl;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.model.client.transaction.FeedItem;
import com.roundup.roundup.model.client.transaction.TransactionResponse;
import com.roundup.roundup.model.dto.AccountDTO;
import com.roundup.roundup.service.RoundUpService;
import com.roundup.roundup.service.SavingsGoalClient;
import com.roundup.roundup.service.TransactionClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * This service is used to do the round up of all the customer transactions
 */
@Service
public class RoundUpServiceImpl implements RoundUpService {

    private final AccountClientImpl accountClientImpl;
    private final TransactionClient transactionClient;
    private final SavingsGoalClient savingsGoalClient;

    public RoundUpServiceImpl(AccountClientImpl accountClientImpl, TransactionClient transactionClient, SavingsGoalClient savingsGoalClient) {
        this.accountClientImpl = accountClientImpl;
        this.transactionClient = transactionClient;
        this.savingsGoalClient = savingsGoalClient;
    }

    /**
     * The main method to do round up
     * It first account details of a customer and then fetch the transaction and do the round up.
     * NOTE : Considering here only the first AccountID transactions
     *
     * @param authorization
     * @return savingsGoalResponse of type {@link com.roundup.roundup.model.client.saving.SavingsGoalResponse}
     */
    @Override
    public SavingsGoalResponse roundUp(final String authorization) {
        List<AccountDTO> response = accountClientImpl.getAccounts(authorization);
        //Considering here only the first AccountID as the Transaction and saving goal apis works on the account id
        AccountDTO accountDTO = response.get(0);
        TransactionResponse transaction = transactionClient.getTransaction(accountDTO.getAccountUid(), accountDTO.getCategoryId(), authorization);
        BigDecimal roundUpAmount = calculate(transaction.getFeedItems());
        return savingsGoalClient.createAndUpdateSavingGoal(accountDTO.getAccountUid(), roundUpAmount, authorization);
    }

    /**
     * Takes all the customer transaction and return the round up amount
     *
     * @param transactions list of type  {@link com.roundup.roundup.model.client.transaction.FeedItem}
     * @return round up amount of type {@link java.math.BigDecimal}
     */
    private BigDecimal calculate(final List<FeedItem> transactions) {
        BigDecimal roundUpAmount = transactions.stream().filter
                        (transaction -> transaction.getAmount().getMinorUnits().compareTo(BigDecimal.ZERO) > 0)
                .map(this::getRoundupAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return roundUpAmount.setScale(0, RoundingMode.UP).multiply(new BigDecimal(100));
    }


    /**
     * Round up the Transaction amount
     *
     * @param transaction of type {@link com.roundup.roundup.model.client.transaction.FeedItem}
     * @return round up amount of type {@link java.math.BigDecimal}
     */
    private BigDecimal getRoundupAmount(FeedItem transaction) {
        final BigDecimal conversionBigdecimal = new BigDecimal(100);
        BigDecimal amount = transaction.getAmount().getMinorUnits().divide(conversionBigdecimal);
        return amount.setScale(0, RoundingMode.UP).subtract(amount);
    }
}
