package com.chen.domain.account.vo;

import com.chen.shared.ValueObject;
import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author chen
 */
@Embeddable
public final class AccountId implements ValueObject<AccountId> {
    private static final long serialVersionUID = 5767729881640613792L;

    @Column(name = "id", columnDefinition = "varchar(20)")
    private String id;

    public AccountId(final String id) {
        Preconditions.checkNotNull(id, "帐号ID不能为空");
        this.id = id;
    }

    protected AccountId() {
        // Hibernate
    }

    public String idString() {
        return this.id;
    }

    @Override
    public boolean sameValueAs(AccountId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountId other = (AccountId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
