package com.chen.service.dto;

/**
 * @author chen
 */
public class WechatAccountDTO {
    private String nickName;
    private String openId;
    private String avatarUrl;
    private String gender;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "WechatAccountDTO{" +
            "nickName='" + nickName + '\'' +
            ", openId='" + openId + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", gender='" + gender + '\'' +
            '}';
    }
}
