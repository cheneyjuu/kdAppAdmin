package com.chen.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chen
 */
public class CardNoUtil {
    private static int i = 0;

    /**
     * 需要传入一个前缀：6、8、9中的一个。
     * 其中：6：邦能卡，  8：邦能金卡， 9：邦能黑金卡
     * 其他则会返回异常
     *
     * @param prefix prefix
     * @return
     */
    public synchronized static String getCardNumber(String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            if ("689".contains(prefix) && prefix.length() == 1) {
                String st = "6888" + prefix + getUnixTime();
                return st + getBankCardCheckCode(st);
            } else {
                System.out.println("银行卡号前缀有误");
                return null;
            }
        } else {
            System.out.println("银行卡号去前缀不能是空");
            return null;
        }
    }

    /***
     * 获取当前系统时间戳 并截取
     */
    private synchronized static String getUnixTime() {
        try {
            //线程同步执行，休眠10毫秒 防止卡号重复
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        i = i > 100 ? i % 10 : i;
        return ((System.currentTimeMillis() / 100) + "").substring(1) + (i % 10);
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
            || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getCardNumber("8"));
            System.out.println(getCardNumber("6"));
            System.out.println(getCardNumber("9"));
        }

    }
}
