package com.roundup.roundup.service;

import com.roundup.roundup.model.dto.AccountDTO;

import java.util.List;

public interface AccountClient {

    List<AccountDTO> getAccounts(final String authorization);
}
