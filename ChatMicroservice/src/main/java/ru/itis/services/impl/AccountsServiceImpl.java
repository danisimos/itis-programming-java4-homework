package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.AccountDto;
import ru.itis.dto.mappers.AccountMapper;
import ru.itis.exceptions.AccountNotFoundException;
import ru.itis.exceptions.CustomValidationException;
import ru.itis.exceptions.ExceptionEntity;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.services.AccountsService;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final Validator validator;

    @Override
    public List<AccountDto> getUsers() {
        return accountMapper.toAccountDtoList(accountsRepository.findAll());
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Set<ConstraintViolation<AccountDto>> violations = validator.validate(accountDto);
        if(!violations.isEmpty()) {
            throw new CustomValidationException(ExceptionEntity.valueOf(violations.stream().findFirst().get().getMessage()));
        }

        Account account = accountMapper.toAccount(accountDto);
        account.setState(Account.State.ACTIVE);
        account.setRole(Account.Role.USER);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto accountDto) {
        Account account = accountsRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException(ExceptionEntity.ACCOUNT_NOT_DOUND));

        accountMapper.updateAccountFromDto(accountDto, account);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }

    @Override
    public AccountDto deleteAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException(ExceptionEntity.ACCOUNT_NOT_DOUND));

        account.setState(Account.State.DELETED);

        return accountMapper.toAccountDto(accountsRepository.save(account));
    }
}
