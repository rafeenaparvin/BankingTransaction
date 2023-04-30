package com.project.banking.BankingSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    private String sourceAccountNumber;
    private String beneficiaryAccountNumber;
    private BigDecimal amount;
}
