package com.chen.domain.account;

import com.chen.domain.account.vo.AccountAuthorityId;
import com.chen.shared.BaseEntity;

import javax.persistence.*;

/**
 * @author chen
 */
@Entity
@Table(name = "account_authority")
@IdClass(AccountAuthorityId.class)
public class AccountAuthority implements BaseEntity<AccountAuthority> {
    @Id
    @Column(columnDefinition = "varchar(50)")
    private String authority;
    @Id
    @Column(columnDefinition = "varchar(20)")
    private String accountId;

    protected AccountAuthority() {
    }

    public AccountAuthority(String authority, String accountId) {
        this.authority = authority;
        this.accountId = accountId;
    }

    @Override
    public boolean sameIdentityAs(AccountAuthority other) {
        return authority.equals(other.authority) && accountId.equals(other.accountId);
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
