package mytest.shirodemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import mytest.shirodemo.entity.Account;
import mytest.shirodemo.mapper.AccountMapper;
import mytest.shirodemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
        lqw.eq(username != null, Account::getUsername, username);
        return accountMapper.selectOne(lqw);  // 直接返回查询结果
    }
}
