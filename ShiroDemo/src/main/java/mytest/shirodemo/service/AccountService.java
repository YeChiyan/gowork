package mytest.shirodemo.service;

import mytest.shirodemo.entity.Account;

public interface AccountService {
    public Account findByUsername(String username);
}
