package com.chen.domain.account.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author chen
 */
public class AccountAuthorityId implements Serializable {
    private static final long serialVersionUID = -8604175473000667797L;

    private String authority;
    private String accountId;

    public AccountAuthorityId(String authority, String accountId) {
        this.authority = authority;
        this.accountId = accountId;
    }

    public AccountAuthorityId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountAuthorityId that = (AccountAuthorityId) o;
        return Objects.equals(authority, that.authority) &&
            Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, accountId);
    }
}
