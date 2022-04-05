package ru.itis.services;

import ru.itis.dto.AccountDto;

import java.util.List;

public interface AccountsService {
    List<AccountDto> getUsers();

    AccountDto addAccount(AccountDto accountDto);

    AccountDto updateAccount(Long accountId, AccountDto accountDto);

    AccountDto deleteAccount(Long accountId);
}
