package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AccountDto;
import ru.itis.services.AccountsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(accountsService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountsService.addAccount(accountDto));
    }

    @PutMapping("{account-id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("account-id") Long accountId,
                                                    @RequestBody AccountDto accountDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountsService.updateAccount(accountId, accountDto));
    }

    @DeleteMapping("{account-id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountsService.deleteAccount(accountId));
    }
}
