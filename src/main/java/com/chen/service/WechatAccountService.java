package com.chen.service;

import com.chen.domain.account.Account;
import com.chen.domain.account.AccountFactory;
import com.chen.domain.account.event.AccountCreatedEvent;
import com.chen.repository.AccountRepository;
import com.chen.service.dto.WechatAccountDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author chen
 */
@Service
public class WechatAccountService {
    private final AccountRepository accountRepository;
    private final AccountFactory accountFactory;
    private final ApplicationContext applicationContext;

    public WechatAccountService(AccountRepository accountRepository, AccountFactory accountFactory, ApplicationContext applicationContext) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.applicationContext = applicationContext;
    }

    public void createOrUpdate(final WechatAccountDTO payload) {
        Optional<Account> optional = accountRepository.findByLogin(payload.getOpenId());
        Account entity = optional.orElseGet(() -> accountFactory.createAccount(payload.getOpenId(), payload.getOpenId(), payload.getNickName(), "1".equals(payload.getGender()) ? "男" : "女", payload.getOpenId() + "@sample.com"));
        entity.withWechat(payload.getOpenId(), payload.getAvatarUrl());
        Account account = accountRepository.save(entity);
        applicationContext.publishEvent(new AccountCreatedEvent(this, account.getAccountId()));
    }
}
