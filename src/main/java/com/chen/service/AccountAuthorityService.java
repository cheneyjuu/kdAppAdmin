package com.chen.service;


import com.chen.domain.account.Account;
import com.chen.domain.account.AccountAuthority;
import com.chen.domain.account.event.AccountCreatedEvent;
import com.chen.repository.AccountAuthorityRepository;
import com.chen.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.chen.config.Constants.ROLE_USER;

/**
 * @author chen
 */
@Service
public class AccountAuthorityService {
    private static final Logger log = LoggerFactory.getLogger(AccountAuthorityService.class);
    private static final String DEFAULT_AUTHORITY = "ROLE_WHITE_COLLAR";

    private final AccountRepository accountRepository;
    private final AccountAuthorityRepository accountAuthorityRepository;

    public AccountAuthorityService(AccountRepository accountRepository, AccountAuthorityRepository accountAuthorityRepository) {
        this.accountRepository = accountRepository;
        this.accountAuthorityRepository = accountAuthorityRepository;
    }

    @EventListener
    public void addDefaultAuthorityForAccount(AccountCreatedEvent event) {
        log.info("监听到帐号创建完毕事件");
        Optional<Account> optional = accountRepository.findByAccountId(event.getAccountId());
        log.info("创建的帐号 => {}", optional);
        if (optional.isPresent()) {
            AccountAuthority accountAuthority = new AccountAuthority(DEFAULT_AUTHORITY, event.getAccountId().idString());
            AccountAuthority userAuth = new AccountAuthority(ROLE_USER, event.getAccountId().idString());
            accountAuthorityRepository.save(accountAuthority);
            accountAuthorityRepository.save(userAuth);
        }
    }
}
