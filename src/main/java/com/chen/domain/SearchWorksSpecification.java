package com.chen.domain;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchWorksSpecification implements Specification<Works> {
    private static final long serialVersionUID = -1181153347106608665L;

    private final String workCategory;
    private final Integer verifyStatus;

    public SearchWorksSpecification(String workCategory, Integer verifyStatus) {
        this.workCategory = workCategory;
        this.verifyStatus = verifyStatus;
    }

    @Override
    public Predicate toPredicate(@Nonnull Root<Works> root, @Nonnull CriteriaQuery<?> query, @Nonnull CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        predicate.getExpressions().add(
            cb.and(
                cb.equal(root.get("workCategory"), workCategory),
                cb.equal(root.get("status"), verifyStatus)
            )
        );
        query.where(predicate).orderBy(new OrderImpl(root.get("submitTime")).reverse()).orderBy(new OrderImpl(root.get("workRank")));
        return null;
    }
}
