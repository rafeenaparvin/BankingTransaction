package com.project.banking.BankingSolution.repository;

import com.project.banking.BankingSolution.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(Long accountId);

    Account findByAccountNumber(String accountNumber);
}
