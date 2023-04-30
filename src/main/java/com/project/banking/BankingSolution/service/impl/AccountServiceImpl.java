package com.project.banking.BankingSolution.service.impl;

import com.project.banking.BankingSolution.constants.TransactionType;
import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.entity.Transaction;
import com.project.banking.BankingSolution.model.AccountStatement;
import com.project.banking.BankingSolution.model.Transfer;
import com.project.banking.BankingSolution.repository.AccountRepository;
import com.project.banking.BankingSolution.repository.TransactionRepository;
import com.project.banking.BankingSolution.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String save(Account account) {
        account.setAccountNumber(UUID.randomUUID().toString());
        Account accountFromDB = accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountFromDB.getAccountNumber());
        transaction.setAmount(account.getBalance());
        transaction.setTransactionType("CREDIT");
        transactionRepository.save(transaction);
        return accountFromDB.getAccountNumber();
    }

    @Override
    public Long sendMoney(Transfer transfer) {
        String sourceAccountNumber = transfer.getSourceAccountNumber();
        String beneficiaryAccountNumber = transfer.getBeneficiaryAccountNumber();
        BigDecimal amount = transfer.getAmount();
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
        Account beneficiaryAccount = accountRepository.findByAccountNumber(beneficiaryAccountNumber);

        if (sourceAccount.getBalance().compareTo(amount) > 0) {
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
            accountRepository.save(sourceAccount);

            beneficiaryAccount.setBalance(beneficiaryAccount.getBalance().add(amount));
            accountRepository.save(beneficiaryAccount);

            Transaction transaction = transactionRepository.save(new Transaction(0l, sourceAccountNumber,
                    amount, "USD", TransactionType.DEBIT.toString()));
            transactionRepository.save(new Transaction(0l, beneficiaryAccountNumber, amount, "USD",
                    TransactionType.CREDIT.toString()));
            return transaction.getTransactionId();
        }
        return null;
    }

    @Override
    public AccountStatement getStatement(String accountNumber) {
        return new AccountStatement(accountNumber, transactionRepository.findByAccountNumber(accountNumber));
    }

}
