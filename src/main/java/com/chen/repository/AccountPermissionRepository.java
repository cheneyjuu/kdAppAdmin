package com.chen.repository;

import com.chen.domain.account.AccountPermission;
import com.chen.domain.account.vo.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chen
 */
public interface AccountPermissionRepository extends JpaRepository<AccountPermission, Long> {
    List<AccountPermission> findAllByAccountId(AccountId accountId);
}
