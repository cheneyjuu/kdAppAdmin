package com.chen.domain.account.event;

import com.chen.domain.account.vo.AccountId;
import org.springframework.context.ApplicationEvent;

/**
 * @author chen
 */
public class AccountCreatedEvent extends ApplicationEvent {
    private static final long serialVersionUID = -6106937414315079432L;
    private AccountId accountId;

    public AccountCreatedEvent(Object source, AccountId accountId) {
        super(source);
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountId accountId) {
        this.accountId = accountId;
    }
}
