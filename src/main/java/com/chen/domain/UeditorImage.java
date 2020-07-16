package com.chen.domain;

import java.io.Serializable;

/**
 * @author chen
 */
public class UeditorImage implements Serializable {
    private static final long serialVersionUID = 116613874740498901L;
    private String state;
    private String url;
    private String title;
    private String original;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
