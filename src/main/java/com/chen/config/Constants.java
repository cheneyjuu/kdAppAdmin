package com.chen.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String SHORT_DATE_REGEX = "^\\d{4}(-)(1[0-2]|0?\\d)\\1([0-2]\\d|\\d|30|31)$";

    public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String FULL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "zh-cn";
    public static final String ANONYMOUS_USER = "anonymousUser";

    public static final String ROLE_WHITE_COLLAR = "ROLE_WHITE_COLLAR";
    public static final String ROLE_BUILDING = "ROLE_BUILDING";
    public static final String ROLE_USER = "ROLE_USER";

    public static final String MAP_KEY = "7d9fbeb43e975cd1e9477a7e5d5e192a";
    public static final String GAODE_MAP_KEY = "ec9acbe492bb8092937e341e197df061";

    private Constants() {
    }
}
