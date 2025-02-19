package mytest.shirodemo.mapper;

import mytest.shirodemo.entity.Account;
import mytest.shirodemo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @Test
    void test(){
        accountService.findByUsername("zs");
//        System.out.println(accountMapper.selectList(null));
    }
}