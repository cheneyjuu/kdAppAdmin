package com.chen.domain.account;

import com.chen.config.Constants;
import com.chen.domain.account.vo.AccountId;
import com.chen.domain.account.vo.Person;
import com.chen.domain.account.vo.WechatInfo;
import com.chen.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author chen
 */

@Entity
@Table(name = "idms_account")
public class Account implements BaseEntity<Account> {
    private static final long serialVersionUID = 3547218546787586897L;

    @EmbeddedId
    private AccountId accountId;

    @Embedded
    private Person person;

    @Embedded
    private WechatInfo wechatInfo;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;
    /**
     * 用户积分
     */
    private BigDecimal point;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)

    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private LocalDateTime resetDate = null;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    protected Account() {
    }

    protected Account(AccountId accountId, String login, String encryptedPassword, String userName, String gender, String email, String langKey) {
        Preconditions.checkNotNull(accountId, "id不能为空");
        this.accountId = accountId;
        this.login = login;
        this.password = encryptedPassword;
        this.point = BigDecimal.ZERO;
        this.activated = true;
        if (StringUtils.isEmpty(langKey)) {
            this.person = new Person(userName, gender, email, Constants.DEFAULT_LANGUAGE, null);
        } else {
            this.person = new Person(userName, gender, email, langKey, null);
        }
        this.createdDate = LocalDateTime.now();
    }

    public void withWechat(final String openId, final String avatar) {
        Preconditions.checkNotNull(login, "登录名不能为空");
        Preconditions.checkNotNull(password, "密码不能为空");

        this.wechatInfo = new WechatInfo(openId, avatar);
    }

    public AccountId accountId() {
        return this.accountId;
    }

    /**
     * 停用帐号
     */
    public void stopAccount() {
        this.activated = false;
    }

    /**
     * 累计积分
     *
     * @param point 积分
     */
    public void increasePoint(BigDecimal point) {
        if (null == this.point) {
            this.point = BigDecimal.ZERO;
        }
        this.point = this.point.add(point);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Account)
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Account other = (Account) object;
        return sameIdentityAs(other);
    }

    /**
     * @return Hash code of tracking id.
     */
    @Override
    public int hashCode() {
        return accountId.hashCode();
    }

    @Override
    public String toString() {
        return accountId.toString();
    }

    @Override
    public boolean sameIdentityAs(Account other) {
        return other != null && accountId.sameValueAs(other.accountId);
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountId accountId) {
        this.accountId = accountId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public LocalDateTime getResetDate() {
        return resetDate;
    }

    public void setResetDate(LocalDateTime resetDate) {
        this.resetDate = resetDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public WechatInfo getWechatInfo() {
        return wechatInfo;
    }
}
