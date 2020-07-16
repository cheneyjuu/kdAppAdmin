package com.chen.repository;

import com.chen.domain.account.AccountAuthority;
import com.chen.domain.account.vo.AccountAuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author chen
 */
public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, AccountAuthorityId> {
    List<AccountAuthority> findAllByAccountId(String id);

    Optional<AccountAuthority> findByAccountIdAndAuthority(String accountId, String authority);
}
