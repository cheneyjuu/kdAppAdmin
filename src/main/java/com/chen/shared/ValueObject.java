package com.chen.shared;

import java.io.Serializable;

/**
 * @author chen
 */
public interface ValueObject<T> extends Serializable {
    /**
     * 和其它值对象对比
     *
     * @param other 其它值对象
     * @return 如果是同一个值对象，返回 {@code true}
     */
    boolean sameValueAs(T other);
}
