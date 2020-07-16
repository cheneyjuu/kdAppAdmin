package com.chen.service.dto;

import com.chen.domain.account.Account;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import static com.chen.config.Constants.SHORT_DATE_PATTERN;

/**
 * @author chen
 */
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -1185849842276392212L;
    private String id;
    private String login;
    private String password;
    private String userName;
    private String nickName;
    private String openId;
    private String avatar;
    private String gender;
    private String email;
    private String createdDate;
    private String userType;

    public AccountDTO(Account account, String userType) {
        this.id = account.getAccountId().idString();
        this.login = account.getLogin();
        this.userName = account.getPerson().getUserName();
        this.openId = account.getWechatInfo().getOpenId();
        this.avatar = account.getWechatInfo().getAvatar();
        this.gender = account.getPerson().getGender();
        this.createdDate = account.getCreatedDate().format(DateTimeFormatter.ofPattern(SHORT_DATE_PATTERN));
        this.userType = userType;
    }

    public AccountDTO(Account account) {
        this.id = account.getAccountId().idString();
        this.login = account.getLogin();
        this.userName = account.getPerson().getUserName();
        this.openId = account.getWechatInfo().getOpenId();
        this.avatar = account.getWechatInfo().getAvatar();
        this.gender = account.getPerson().getGender();
        this.createdDate = account.getCreatedDate().format(DateTimeFormatter.ofPattern(SHORT_DATE_PATTERN));
    }

    public AccountDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserType() {
        return userType;
    }
}
