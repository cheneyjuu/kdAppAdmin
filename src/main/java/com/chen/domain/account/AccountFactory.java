package com.chen.domain.account;

import com.chen.config.Constants;
import com.chen.domain.account.vo.AccountId;
import com.github.wujun234.uid.UidGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author chen
 */
@Service
public class AccountFactory {
    private final PasswordEncoder passwordEncoder;
    private final UidGenerator defaultUidGenerator;

    public AccountFactory(PasswordEncoder passwordEncoder, UidGenerator defaultUidGenerator) {
        this.passwordEncoder = passwordEncoder;
        this.defaultUidGenerator = defaultUidGenerator;
    }

    public Account createAccount(String login, String password, String userName, String gender, String email) {
        String encryptedPassword = passwordEncoder.encode(password);
        long id = defaultUidGenerator.getUID();
        AccountId accountId = new AccountId(Long.toString(id));
        return new Account(accountId, login, encryptedPassword, userName, gender, email, Constants.DEFAULT_LANGUAGE);
    }
}
