package com.chen.domain.account.vo;

import com.chen.shared.ValueObject;

import javax.persistence.Embeddable;

/**
 * @author chen
 */
@Embeddable
public final class WechatInfo implements ValueObject<WechatInfo> {
    private static final long serialVersionUID = -7594354839276432787L;

    private final String openId;
    private final String avatar;

    public WechatInfo(String openId, String avatar) {
        this.openId = openId;
        this.avatar = avatar;
    }

    protected WechatInfo() {
        this.openId = null;
        this.avatar = null;
    }

    @Override
    public boolean sameValueAs(WechatInfo other) {
        return openId.equals(other.openId);
    }

    public String getOpenId() {
        return openId;
    }

    public String getAvatar() {
        return avatar;
    }
}
