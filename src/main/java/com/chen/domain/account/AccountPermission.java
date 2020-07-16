package com.chen.domain.account;

import com.chen.domain.account.vo.AccountId;
import com.chen.shared.BaseEntity;

import javax.persistence.*;

/**
 * @author chen
 */
@Entity
@Table(name = "account_permission")
public final class AccountPermission implements BaseEntity<AccountPermission> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Long id;
    @Column(name = "permissions", columnDefinition = "varchar(2000) COMMENT '帐号拥有的权限点，多个权限用逗号隔开，比如USER-EDIT,USER-LIST'")
    private String permissions;
    private AccountId accountId;

    public AccountPermission(String permissions, AccountId accountId) {
        this.permissions = permissions;
        this.accountId = accountId;
    }

    protected AccountPermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountId accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean sameIdentityAs(AccountPermission other) {
        return id.equals(other.id);
    }
}
