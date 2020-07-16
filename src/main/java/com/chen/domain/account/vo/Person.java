package com.chen.domain.account.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author chen
 */
@Embeddable
public class Person {
    @Size(max = 50)
    @Column(name = "user_name", length = 50)
    String userName;

    String gender;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    String email;

    @Size(min = 2, max = 10)
    @Column(name = "lang_key", length = 10)
    String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    String imageUrl;

    protected Person() {
        this.userName = null;
        this.gender = null;
        this.email = null;
        this.langKey = null;
        this.imageUrl = null;
    }

    public Person(String userName, String gender, String email, String langKey, String imageUrl) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.langKey = langKey;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
