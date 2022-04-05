package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.dto.mappers.AccountMapper;
import ru.itis.exceptions.AccountNotFoundException;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.services.AccountsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getUsers() {
        return accountMapper.toAccountDtoList(accountsRepository.findAll());
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account = accountMapper.toAccount(accountDto);
        account.setState(Account.State.ACTIVE);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto accountDto) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        accountMapper.updateAccountFromDto(accountDto, account);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto deleteAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        account.setState(Account.State.DELETED);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
