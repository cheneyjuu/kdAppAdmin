package com.chen.security;

import com.chen.domain.account.Account;
import com.chen.domain.account.AccountAuthority;
import com.chen.domain.account.AccountPermission;
import com.chen.repository.AccountAuthorityRepository;
import com.chen.repository.AccountPermissionRepository;
import com.chen.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen
 */
@Component("accountDetailsService")
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountAuthorityRepository accountAuthorityRepository;
    private final AccountPermissionRepository accountPermissionRepository;

    public AccountDetailsService(AccountRepository accountRepository, AccountAuthorityRepository accountAuthorityRepository, AccountPermissionRepository accountPermissionRepository) {
        this.accountRepository = accountRepository;
        this.accountAuthorityRepository = accountAuthorityRepository;
        this.accountPermissionRepository = accountPermissionRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        return accountRepository.findByLogin(login)
            .map(account -> createSpringSecurityUser(login, account))
            .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }

    private User createSpringSecurityUser(String login, Account account) {
        if (!account.isActivated()) {
            throw new UserNotActivatedException("User " + login + " was not activated");
        }
        List<String> authorityNames = accountAuthorityRepository.findAllByAccountId(account.getAccountId().idString()).parallelStream()
            .map(AccountAuthority::getAuthority).collect(Collectors.toList());
        List<GrantedAuthority> grantedAuthorities = authorityNames.stream().map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        List<String> permissionNames = accountPermissionRepository.findAllByAccountId(account.getAccountId()).parallelStream()
            .map(AccountPermission::getPermissions).collect(Collectors.toList());
        if (permissionNames.size() > 0) {
            List<GrantedAuthority> grantedPermissions = permissionNames.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            grantedAuthorities.addAll(grantedPermissions);
        }
        return new User(account.getLogin(), account.getPassword(), grantedAuthorities);
    }
}
