package com.chen.domain.account;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

import static com.chen.config.Constants.SHORT_DATE_REGEX;

/**
 * 帐号分页查询
 *
 * @author chen
 */
public class PageSearchAccountSpecification implements Specification<Account> {
    private static final Logger log = LoggerFactory.getLogger(PageSearchAccountSpecification.class);
    private final String startDate;
    private final String endDate;

    public PageSearchAccountSpecification(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (!StringUtils.isBlank(startDate) && !StringUtils.isBlank(endDate)) {
            if (!startDate.matches(SHORT_DATE_REGEX) || !endDate.matches(SHORT_DATE_REGEX)) {
                log.warn("帐号分页查询 -- 日期参数有误 ==> {} - {}", startDate, endDate);
                throw new IllegalArgumentException("日期格式错误");
            }
            predicate.getExpressions().add(
                cb.between(
                    root.get("createdDate"),
                    LocalDate.parse(startDate).atTime(0, 0, 0),
                    LocalDate.parse(endDate).atTime(23, 59, 59)
                )
            );
        }
        query.where(predicate);
        return query.getRestriction();
    }
}
