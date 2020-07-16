package com.chen.service;


import com.chen.domain.account.Account;
import com.chen.domain.account.AccountAuthority;
import com.chen.domain.account.AccountFactory;
import com.chen.domain.account.PageSearchAccountSpecification;
import com.chen.repository.AccountAuthorityRepository;
import com.chen.repository.AccountRepository;
import com.chen.security.SecurityUtils;
import com.chen.service.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.chen.config.Constants.ANONYMOUS_USER;


/**
 * @author chen
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountFactory accountFactory;
    private final AccountAuthorityRepository accountAuthorityRepository;

    public AccountService(AccountRepository accountRepository, AccountFactory accountFactory,
                          AccountAuthorityRepository accountAuthorityRepository) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.accountAuthorityRepository = accountAuthorityRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public String createAccount(AccountDTO dto) {
        Account entity = accountFactory.createAccount(dto.getLogin(), dto.getGender(), dto.getPassword(), dto.getUserName(), dto.getEmail());
        Account account = accountRepository.save(entity);
        return account.accountId().idString();
    }

    @Transactional(readOnly = true)
    public Page<AccountDTO> pageSearchAccount(String startDate, String endDate, Pageable pageable) {
        Page<Account> page = accountRepository.findAll(new PageSearchAccountSpecification(startDate, endDate), pageable);
        List<AccountDTO> dtoList = page.getContent().parallelStream().map(account -> {
            AtomicReference<String> userType = new AtomicReference<>();
            List<AccountAuthority> authorityList = accountAuthorityRepository.findAllByAccountId(account.getAccountId().idString());
            return new AccountDTO(account, userType.get());
        }).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Transactional(readOnly = true)
    public AccountDTO getCurrentAccountInfo() {
        String login = SecurityUtils.getCurrentUserLogin().orElse(ANONYMOUS_USER);
        if (!login.equals(ANONYMOUS_USER)) {
            return accountRepository.findByLogin(login).map(AccountDTO::new).orElse(null);
        }
        return null;
    }
}
