package com.chen.shared;

/**
 * @author chen
 */
public interface BaseEntity<T> {
    /**
     * 实体对比
     *
     * @param other 其它实体
     * @return 如果两个实体的id一样，返回 {@code true}
     */
    boolean sameIdentityAs(T other);
}
