package com.project.banking.BankingSolution.service.impl;


import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.model.Transaction;
import com.project.banking.BankingSolution.repository.AccountRepository;
import com.project.banking.BankingSolution.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal convertMoney(Transaction transaction) {
        double rate;
        switch (transaction.getCurrency()) {
            case "USD":
                rate = 1.0;
                break;
            case "INR":
                rate = 0.012;
                break;
            case "EUR":
                rate = 1.11;
                break;
            case "CAD":
                rate = 0.73;
                break;
            case "QAR":
                rate = 0.27;
                break;
            case "AED":
                rate = 0.270;
                break;
            default:
                rate = 1;
        }
        return transaction.getAmount().multiply(BigDecimal.valueOf(rate));
    }

    @Override
    public void creditMoney(Transaction transaction) {
        BigDecimal finalAmount = convertMoney(transaction);
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository
                .findByAccountNumber(transaction.getSourceAccountId()));
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance().add(finalAmount));
            accountRepository.save(account);
        }
    }

    @Override
    public void debitMoney(Transaction transaction) {
        BigDecimal finalAmount = convertMoney(transaction);
        Optional<Account> accountOptional = Optional.ofNullable(
                accountRepository.findByAccountNumber(transaction.getSourceAccountId()));
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance().subtract(finalAmount));
            accountRepository.save(account);
        }
    }
}

