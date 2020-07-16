package com.chen.repository;

import com.chen.domain.account.Account;
import com.chen.domain.account.vo.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author chen
 */
public interface AccountRepository extends JpaRepository<Account, AccountId>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByLogin(String login);

    Optional<Account> findByAccountId(AccountId id);
}
