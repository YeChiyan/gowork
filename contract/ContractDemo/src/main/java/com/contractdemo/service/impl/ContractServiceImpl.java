package com.contractdemo.service.impl;

import com.contractdemo.mapper.ContractLedgerMapper;
import com.contractdemo.mapper.ExpenditureContractMapper;
import com.contractdemo.mapper.IncomeContractMapper;
import com.contractdemo.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractLedgerMapper ledgerMapper;

    @Autowired
    private ExpenditureContractMapper expenditureMapper;

    @Autowired
    private IncomeContractMapper incomeMapper;


}
